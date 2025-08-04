package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository


class GetCharacterDataBaseUseCase(
    private val characterDataBaseRepository: CharacterDataBaseRepository
) {
    suspend fun execute(id: Int): CharacterData?{
        return characterDataBaseRepository.getById(id)
    }
}