package com.example.rickandmortyapplication.presentation.fragments.detailsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapplication.domain.usecase.GetNumCharacterUseCase
import com.example.rickandmortyapplication.presentation.fragments.detailsfragment.model.DetailsView
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.domain.usecase.GetCharacterDataBaseUseCase
import com.example.rickandmortyapplication.domain.usecase.GetCharacterUseCase
import com.example.rickandmortyapplication.manager.ResourcesManager
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.CharacterForRecycle
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.Images
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.UIState
import com.example.rickandmortyapplication.presentation.fragments.detailsfragment.model.UIDetailsState
import com.example.rickandmortyapplication.utils.ResultProcessing
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getNumCharacterUseCase: GetNumCharacterUseCase,
    val getCharacterUseCase: GetCharacterUseCase,
    val getCharacterDataBaseUseCase: GetCharacterDataBaseUseCase,
    val resourcesManager: ResourcesManager
) : ViewModel() {
    private val state = MutableLiveData<DetailsView>()
    val _state: LiveData<DetailsView> = state

    private val uiState = MutableLiveData<UIDetailsState>(UIDetailsState.Loading)
    val _uiState: LiveData<UIDetailsState> = uiState

    init {
        compareData()
    }

    private fun compareData() {
        uiState.value = UIDetailsState.Loading
        viewModelScope.launch {
            val id = getNumCharacterUseCase.execute().number
            val result = getCharacterUseCase.execute(id)
            when (result) {
                is ResultProcessing.Error -> {
                    val character = getCharacterDataBaseUseCase.execute(id)
                    when (character == null) {
                        true -> uiState.value = UIDetailsState.Error(message = result.message)
                        false -> {
                            uiState.value = UIDetailsState.Success
                            state.value = DetailsView(
                                name = character.name,
                                image = character.imageUrl,
                                information = resourcesManager.getString(
                                    R.string.details,
                                    character.name,
                                    character.gender,
                                    character.species,
                                    if (character.type.isEmpty()) "" else "(${character.type})",
                                    character.status,
                                    character.origin,
                                    character.location
                                )
                            )
                        }
                    }
                }


                ResultProcessing.Loading -> {
                    uiState.value = UIDetailsState.Loading
                }

                is ResultProcessing.Success -> {
                    uiState.value = UIDetailsState.Success
                    state.value = DetailsView(
                        name = result.data.name,
                        image = result.data.imageUrl,
                        information = resourcesManager.getString(
                            R.string.details,
                            result.data.name,
                            result.data.gender,
                            result.data.species,
                            if (result.data.type.isEmpty()) "" else "(${result.data.type})",
                            result.data.status,
                            result.data.origin,
                            result.data.location
                        )
                    )
                }
            }

        }
    }
}