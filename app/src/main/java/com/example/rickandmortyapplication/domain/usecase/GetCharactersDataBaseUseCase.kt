package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository

class GetCharactersDataBaseUseCase(
    private val characterDataBaseRepository: CharacterDataBaseRepository
) {
    suspend fun execute(filter: FilterData): List<CharacterData?> {
        return characterDataBaseRepository.getQuery(filter)
    }
}