package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.model.FilterData

interface FilterCharacterRepository {
    suspend fun getCharacters(page: Int, filter: FilterData): CharacterResponse
}