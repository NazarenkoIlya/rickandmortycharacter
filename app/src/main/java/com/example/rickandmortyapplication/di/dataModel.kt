package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapplication.domain.repository.CharactersRepository
import org.koin.dsl.module

val dataModule = module {
    single<CharactersRepository> {
        CharacterRepositoryImpl(
            service = get()
        )
    }
}