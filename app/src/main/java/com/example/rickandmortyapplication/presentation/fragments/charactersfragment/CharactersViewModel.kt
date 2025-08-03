package com.example.rickandmortyapplication.presentation.fragments.charactersfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.R
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
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.ViewItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveNumCharacterUseCase: SaveNumCharacterUseCase,
    private val getFilterCharacterUseCase: GetFilterCharacterUseCase,
    private val setFilterCharactersUseCase: SetFilterCharactersUseCase
) : ViewModel() {

    private var currentPage = 1
    private var isRequestRunning = false
    private var isLastPage = false
    private var filterApply = false

    private val state = MutableLiveData<List<ViewItem>>(emptyList<ViewItem>())
    val _state: LiveData<List<ViewItem>?> = state

    private val navigationState = MutableLiveData<Navigation?>()
    val _navigationState: LiveData<Navigation?> = navigationState

    init {
        navigationState.value = null
        compareData()
    }


    fun loadData() {
        compareData()
    }

    private fun compareData() {
        Log.d("AAAAAAA", "do compareData: ${isLastPage}")
        if (isRequestRunning) return

        Log.d("AAAAAAA", "posle compareData: ${isLastPage}")
        viewModelScope.launch {
            //uiState.value = UIState.Loading
            state.value = getCharacters()
        }
    }

    private suspend fun getCharacters(): List<ViewItem> {
        var characterList = state.value!!
        isRequestRunning = true
        try {
            val filter = getFilterCharacterUseCase.execute()

            if (filter.isApply) {
                currentPage = 1
                isLastPage = false
            }

            if (!isLastPage) {
                val data = setFilterCharactersUseCase.execute(currentPage, filter)

                val character = data.character.map { it ->
                    CharacterForRecycle(
                        id = it.id,
                        name = it.name,
                        image = Images(it.imageUrl, R.drawable.ic_error_image),
                        status = it.status,
                        genderAndSpecies = "${it.gender} | ${it.species}"
                    )
                }

                Log.d("AAAAAAA", "getCharacters: ${filter.isApply}")

                characterList = if (data.isApply) {
                    character
                } else {
                    characterList + character
                }


                //characterList = characterList + character

                isLastPage = data.info.next == null
                currentPage++
            }

        } finally {
            isRequestRunning = false
            // _isLoading.value = false
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