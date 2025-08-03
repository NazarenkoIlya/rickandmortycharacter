package com.example.rickandmortyapplication.presentation.fragments.detailsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapplication.domain.usecase.GetNumCharacterUseCase
import com.example.rickandmortyapplication.presentation.fragments.detailsfragment.model.DetailsView
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.domain.usecase.GetCharacterUseCase
import com.example.rickandmortyapplication.manager.ResourcesManager
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getNumCharacterUseCase: GetNumCharacterUseCase,
    val getCharacterUseCase: GetCharacterUseCase,
    val resourcesManager: ResourcesManager
) : ViewModel() {
    private val state = MutableLiveData<DetailsView>()
    val _state: LiveData<DetailsView> = state

    init {
        compareData()
    }

    private fun compareData() {
        viewModelScope.launch {
            val details = getCharacterUseCase.execute(getNumCharacterUseCase.execute().number)
            state.value = DetailsView(
                name = details.name,
                image = details.imageUrl,
                information = resourcesManager.getString(
                    R.string.details,
                    details.name,
                    details.gender,
                    details.species,
                    if (details.type.isEmpty()) "" else "(${details.type})",
                    details.status,
                    details.origin,
                    details.location
                )
            )
        }
    }

    //"The character named Rick Sanchez (ID: 1) is a male human () with a status of 'Alive'. He is originally from Earth (C-137), and his last known location is the Citadel of Ricks."

}