package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.NumberCharacter
import com.example.rickandmortyapplication.domain.repository.CharacterNumberRepository

class SaveNumCharacterUseCase(
    val characterNumberRepository: CharacterNumberRepository
) {
    suspend fun execute(number: Int) {
        characterNumberRepository.saveNumber(NumberCharacter(number = number))
    }
}