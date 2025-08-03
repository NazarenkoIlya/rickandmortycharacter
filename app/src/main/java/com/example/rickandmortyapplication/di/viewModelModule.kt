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
            setFilterCharactersUseCase = get(),
            getFilterCharacterUseCase=get()
        )
    }

    viewModel {
        DetailsViewModel(
            getNumCharacterUseCase = get(),
            getCharacterUseCase = get(),
            resourcesManager = get()
        )
    }

    viewModel {
        FilterViewModel(
            saveFilterCharacterUseCase = get(),
            getFilterCharacterUseCase = get()
        )
    }


    //viewModel { ResultViewModel(get(), get(), get()) }
}