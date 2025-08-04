package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository
import com.example.rickandmortyapplication.domain.repository.CharactersRepository
import java.io.IOException

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {
    suspend fun execute(page: Int): List<CharacterData> {
        return repository.getCharacters(page)
    }
}