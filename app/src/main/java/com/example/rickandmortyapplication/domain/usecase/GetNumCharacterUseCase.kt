package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.NumberCharacter
import com.example.rickandmortyapplication.domain.repository.CharacterNumberRepository

class GetNumCharacterUseCase(
    val characterNumberRepository: CharacterNumberRepository
) {
    suspend fun execute(): NumberCharacter {
        return characterNumberRepository.getNumber()
    }
}