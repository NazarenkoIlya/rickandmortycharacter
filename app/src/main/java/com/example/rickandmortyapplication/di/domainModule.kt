package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.domain.usecase.GetCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.GetCharactersUseCase
import com.example.rickandmortyapplication.domain.usecase.GetFilterCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.GetNumCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SaveFilterCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SaveNumCharacterUseCase
import com.example.rickandmortyapplication.domain.usecase.SetFilterCharactersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCharactersUseCase(
            repository = get()
        )
    }

    factory {
        GetCharacterUseCase(
            characterRepository = get()
        )
    }

    factory {
        GetNumCharacterUseCase(
            characterNumberRepository = get()
        )
    }

    factory {
        SaveNumCharacterUseCase(
            characterNumberRepository = get()
        )
    }

    factory {
        GetFilterCharacterUseCase(
            filterRepository = get()
        )
    }
    factory {
        SaveFilterCharacterUseCase(
            filterRepository = get()
        )
    }
    factory {
        SetFilterCharactersUseCase(
            filterCharacterRepository = get(),
            filterRepository=get()
        )
    }


}