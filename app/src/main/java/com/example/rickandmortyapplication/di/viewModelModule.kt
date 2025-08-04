package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.CharactersViewModel
import com.example.rickandmortyapplication.presentation.fragments.detailsfragment.DetailsViewModel
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.FilterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(
            getCharactersUseCase = get(),
            saveNumCharacterUseCase = get(),
            getFilterCharacterUseCase = get(),
            setFilterCharactersUseCase = get(),
            getCharactersDataBaseUseCase = get(),
        )
    }

    viewModel {
        DetailsViewModel(
            getNumCharacterUseCase = get(),
            getCharacterUseCase = get(),
            getCharacterDataBaseUseCase = get(),
            resourcesManager = get()
        )
    }

    viewModel {
        FilterViewModel(
            saveFilterCharacterUseCase = get(),
            getFilterCharacterUseCase = get()
        )
    }
}