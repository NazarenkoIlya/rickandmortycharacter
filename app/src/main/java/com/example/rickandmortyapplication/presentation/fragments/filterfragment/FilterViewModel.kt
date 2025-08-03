package com.example.rickandmortyapplication.presentation.fragments.filterfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.usecase.GetFilterCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SaveFilterCharacterUseCase
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.FilterView
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.Gender
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.NameChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.OnApplyClicked
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.OnResetClicked
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.OnStateChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.RadioButtonGenderChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.RadioButtonStatusChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.SpeciesChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.Status
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.TypeChanged
import kotlinx.coroutines.launch

class FilterViewModel(
    private val saveFilterCharacterUseCase: SaveFilterCharacterUseCase,
    private val getFilterCharacterUseCase: GetFilterCharacterUseCase
) : ViewModel() {

    private val state = MutableLiveData<FilterView>()
    val _state: LiveData<FilterView> = state

    private val stateButton = MutableLiveData<Boolean>(false)
    val _stateButton: LiveData<Boolean> = stateButton

    lateinit var pervFilter: FilterView

    init {
        compareData()
    }

    private fun setStatus(status: String): Status {
        return when (status) {
            Status.Alive.status -> {
                Status.Alive
            }

            Status.Dead.status -> {
                Status.Dead
            }

            Status.Unknown.status -> {
                Status.Unknown
            }

            else -> {
                Status.None
            }
        }
    }

    private fun setGender(gender: String): Gender {
        return when (gender) {
            Gender.Female.gender -> {
                Gender.Female
            }

            Gender.Male.gender -> {
                Gender.Male
            }

            Gender.Genderless.gender -> {
                Gender.Genderless
            }

            Gender.Unknown.gender -> {
                Gender.Unknown
            }

            else -> {
                Gender.None
            }
        }


    }

    private fun compareData() {
        viewModelScope.launch {
            val filter = getFilterCharacterUseCase.execute().copy(isApply = false)
            pervFilter = FilterView(
                isApply = filter.isApply,
                name = filter.name,
                status = setStatus(filter.status),
                species = filter.species,
                type = filter.type,
                gender = setGender(filter.gender)
            )
            state.value = FilterView(
                isApply = filter.isApply,
                name = filter.name,
                status = setStatus(filter.status),
                species = filter.species,
                type = filter.type,
                gender = setGender(filter.gender)
            )
        }
    }

    private fun changedStateButton() {
        stateButton.value = state.value?.equals(pervFilter)
    }


    fun onEvent(onStateChanged: OnStateChanged) {
        when (onStateChanged) {
            OnApplyClicked -> {
                viewModelScope.launch {
                    val filter = state.value!!
                    saveFilterCharacterUseCase.execute(
                        FilterData(
                            isApply = true,
                            //reset = filter.reset,
                            name = filter.name,
                            status = filter.status.status,
                            species = filter.species,
                            type = filter.type,
                            gender = filter.gender.gender
                        )
                    )
                }
            }

            OnResetClicked -> {
                state.value = state.value?.copy(
                    name = "",
                    status = Status.None,
                    species = "",
                    type = "",
                    gender = Gender.None
                )
            }

            is NameChanged -> {
                if (state.value?.name != onStateChanged.name) {
                    state.value = state.value?.copy(
                        name = onStateChanged.name
                    )
                }
                changedStateButton()
            }


            is RadioButtonGenderChanged -> {
                state.value =
                    state.value?.copy(
                        gender = setGender(
                            onStateChanged.gender
                        )
                    )
                changedStateButton()
            }

            is RadioButtonStatusChanged -> {
                state.value =
                    state.value?.copy(
                        status = setStatus(onStateChanged.status)
                    )
                changedStateButton()
            }

            is SpeciesChanged -> {
                if (state.value?.species != onStateChanged.species) {
                    state.value = state.value?.copy(
                        species = onStateChanged.species
                    )
                }
                changedStateButton()
            }

            is TypeChanged -> {
                if (state.value?.type != onStateChanged.type) {
                    state.value = state.value?.copy(
                        type = onStateChanged.type
                    )
                }
                changedStateButton()
            }
        }
    }
}
