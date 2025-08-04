package com.example.rickandmortyapplication.presentation.fragments.charactersfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.domain.usecase.GetCharactersDataBaseUseCase
import com.example.rickandmortyapplication.domain.usecase.GetCharactersUseCase
import com.example.rickandmortyapplication.domain.usecase.GetFilterCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SaveNumCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SetFilterCharactersUseCase
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.CharacterForRecycle
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.GoToDetail
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.GoToFilters
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.Images
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.Navigation
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.OnFilterClicked
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.OnItemClicked
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.OnStateChanged
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.UIState
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.ViewItem
import com.example.rickandmortyapplication.utils.ResultProcessing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveNumCharacterUseCase: SaveNumCharacterUseCase,
    private val getFilterCharacterUseCase: GetFilterCharacterUseCase,
    private val setFilterCharactersUseCase: SetFilterCharactersUseCase,
    private val getCharactersDataBaseUseCase: GetCharactersDataBaseUseCase
) : ViewModel() {

    private var currentPage = 1
    private var isRequestRunning = false
    private var isLastPage = false


    private val state = MutableLiveData<List<ViewItem?>>(emptyList<ViewItem?>())
    val _state: LiveData<List<ViewItem?>> = state

    private val navigationState = MutableLiveData<Navigation?>()
    val _navigationState: LiveData<Navigation?> = navigationState

    private val uiState = MutableLiveData<UIState>(UIState.Success)
    val _uiState: LiveData<UIState> = uiState

    init {
        navigationState.value = null
        compareData()
    }


    fun loadData() {
        compareData()
    }

    private fun compareData() {
        if (isRequestRunning) return
        viewModelScope.launch {

            state.value = getCharacters()
        }
    }

    private suspend fun getCharacters(): List<ViewItem?> {
        var characterList: List<ViewItem?> = state.value!!
        isRequestRunning = true
        try {
            val filter = getFilterCharacterUseCase.execute()

            if (filter.isApply) {
                currentPage = 1
                isLastPage = false
            }

            if (!isLastPage) {
                uiState.value = UIState.Loading
                val result = setFilterCharactersUseCase.execute(currentPage, filter)

                when (result) {
                    is ResultProcessing.Error -> {
                        val character =
                            getCharactersDataBaseUseCase.execute(filter = filter).map { it ->
                                it?.let {
                                    CharacterForRecycle(
                                        id = it.id,
                                        name = it.name,
                                        image = Images(it.imageUrl, R.drawable.ic_error_image),
                                        status = it.status,
                                        genderAndSpecies = "${it.gender} | ${it.species}"
                                    )
                                }
                            }

                        when (character.isEmpty()) {
                            true -> {
                                uiState.value = UIState.Error(message = result.message)
                            }

                            false -> {
                                uiState.value = UIState.Success
                                characterList = character
                            }
                        }
                    }

                    ResultProcessing.Loading -> {
                        uiState.value = UIState.Loading
                    }

                    is ResultProcessing.Success -> {
                        uiState.value = UIState.Success
                        val character = result.data.character.map { it ->
                            CharacterForRecycle(
                                id = it.id,
                                name = it.name,
                                image = Images(it.imageUrl, R.drawable.ic_error_image),
                                status = it.status,
                                genderAndSpecies = "${it.gender} | ${it.species}"
                            )
                        }


                        characterList =
                            if (result.data.isApply) character else characterList + character

                        isLastPage = result.data.info.next == null
                        currentPage++
                    }
                }
            }


        } finally {
            isRequestRunning = false
        }
        return characterList
    }


    fun onEvent(onStateChange: OnStateChanged) {
        when (onStateChange) {
            is OnItemClicked -> {
                viewModelScope.launch {
                    saveNumCharacterUseCase.execute(onStateChange.numberCharacter)
                }
                navigationState.value = GoToDetail
            }

            OnFilterClicked -> navigationState.value = GoToFilters
        }
    }

    fun resetStateNavigate() {
        if (navigationState.value != null)
            navigationState.value = null
    }
}