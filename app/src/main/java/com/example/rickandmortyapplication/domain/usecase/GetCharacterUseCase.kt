package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.repository.CharacterRepository

class GetCharacterUseCase(
    val characterRepository: CharacterRepository
) {
    suspend fun execute(num: Int): CharacterData{
        return characterRepository.getCharacter(num)
    }
}