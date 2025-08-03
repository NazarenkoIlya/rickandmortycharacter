package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.service.CharacterService
import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    val characterService: CharacterService
) : CharacterRepository {
    override suspend fun getCharacter(number: Int): CharacterData {
        val details = characterService.getCharacter(number)
        return CharacterData(
            id = details.id.toString(),
            name = details.name,
            imageUrl = details.image,
            status = details.status,
            gender = details.gender,
            species = details.species,
            origin = details.origin.name,
            type = details.type,
            location = details.location.name
        )
    }

}