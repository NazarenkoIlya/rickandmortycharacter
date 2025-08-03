package com.example.rickandmortyapplication.domain.repository
import com.example.rickandmortyapplication.domain.model.CharacterData

interface CharactersRepository {
    suspend fun saveCharacters(vararg characters: CharacterData )
    suspend fun getCharacters(page: Int): List<CharacterData>
}