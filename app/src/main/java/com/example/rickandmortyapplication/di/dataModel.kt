package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.data.repository.CharacterDataBaseRepositoryImpl
import com.example.rickandmortyapplication.data.repository.CharacterNumberRepositoryImpl
import com.example.rickandmortyapplication.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapplication.data.repository.CharactersRepositoryImpl
import com.example.rickandmortyapplication.data.repository.FilterCharacterRepositoryImpl
import com.example.rickandmortyapplication.data.repository.FilterRepositoryImpl
import com.example.rickandmortyapplication.data.storage.SharedPrefsCharacterNumberStorage
import com.example.rickandmortyapplication.data.storage.CharacterNumberStorage
import com.example.rickandmortyapplication.data.storage.FilterStorage
import com.example.rickandmortyapplication.data.storage.SharedPrefsFilterStorage
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository
import com.example.rickandmortyapplication.domain.repository.CharacterNumberRepository
import com.example.rickandmortyapplication.domain.repository.CharacterRepository
import com.example.rickandmortyapplication.domain.repository.CharactersRepository
import com.example.rickandmortyapplication.domain.repository.FilterCharacterRepository
import com.example.rickandmortyapplication.domain.repository.FilterRepository
import org.koin.dsl.module

val dataModule = module {
    single<CharactersRepository> {
        CharactersRepositoryImpl(
            service = get()
        )
    }

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            characterService = get()
        )
    }

    single<CharacterNumberRepository> {
        CharacterNumberRepositoryImpl(
            storage = get()
        )
    }

    single<CharacterNumberStorage> {
        SharedPrefsCharacterNumberStorage(
            context = get()
        )
    }

    single<FilterRepository> {
        FilterRepositoryImpl(
            filterStorage = get()
        )
    }

    single<FilterStorage> {
        SharedPrefsFilterStorage(
            context = get(),
            gson = get()
        )
    }
    single<FilterCharacterRepository> {
        FilterCharacterRepositoryImpl(
            charactersFilterService = get()
        )
    }

    single<CharacterDataBaseRepository> {
        CharacterDataBaseRepositoryImpl(
            dao = get()
        )
    }

}