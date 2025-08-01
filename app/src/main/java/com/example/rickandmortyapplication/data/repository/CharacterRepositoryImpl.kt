package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.service.CharacterService
import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.repository.CharactersRepository

class CharacterRepositoryImpl(private val service: CharacterService) : CharactersRepository {
    override suspend fun saveCharacters(vararg characters: CharacterData) {

    }

    override suspend fun getCharacters(page: Int): List<CharacterData> {
        return service.getCharacters(page).results.map { it ->
            CharacterData(
                id = it.id.toString(),
                name = it.name,
                imageUrl = it.image,
                status = it.status,
                gender = it.gender,
                species = it.species,
                origin = it.origin.name,
                location = it.location.name
            )
        }
    }
}