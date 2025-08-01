package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.presentation.fragments.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(
            getCharactersUseCase = get()
        )
    }

    

    //viewModel { ResultViewModel(get(), get(), get()) }
}