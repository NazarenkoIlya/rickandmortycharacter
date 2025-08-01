package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCharactersUseCase(
            repository = get()
        )
    }

}