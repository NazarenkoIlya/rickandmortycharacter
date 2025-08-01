package com.example.rickandmortyapplication.presentation.fragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.domain.usecase.GetCharactersUseCase
import com.example.rickandmortyapplication.presentation.model.CharacterForRecycle
import com.example.rickandmortyapplication.presentation.model.GoToDetail
import com.example.rickandmortyapplication.presentation.model.Images
import com.example.rickandmortyapplication.presentation.model.Navigation
import com.example.rickandmortyapplication.presentation.model.OnFilterClicked
import com.example.rickandmortyapplication.presentation.model.OnItemClicked
import com.example.rickandmortyapplication.presentation.model.OnStateChanged
import com.example.rickandmortyapplication.presentation.model.ViewItem
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val state = MutableLiveData<List<ViewItem>>()
    val _state: LiveData<List<ViewItem>> = state

    private val navigationState = MutableLiveData<Navigation?>()
    val _navigationState: LiveData<Navigation?> = navigationState

    init {
        navigationState.value = null
        compareData()
    }

    private fun compareData() {
        viewModelScope.launch {
            //uiState.value = UIState.Loading
            state.value = getCharacters()
        }
    }

    private suspend fun getCharacters(): List<ViewItem> {

        val character = getCharactersUseCase.execute(1).map { it ->
            CharacterForRecycle(
                id = it.id,
                name = it.name,
                image = Images(it.imageUrl, R.drawable.ic_error_image),
                status = it.status,
                genderAndSpecies = "${it.gender} | ${it.species}"
            )
        }

        return character
    }


    fun onEvent(onStateChange: OnStateChanged) {
        when (onStateChange) {
            OnItemClicked -> navigationState.value = GoToDetail
            OnFilterClicked -> {}
        }
    }
}