package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.FilterData

interface CharacterDataBaseRepository {
    suspend fun insertAll(vararg characters: CharacterData)
    suspend fun getQuery(filter: FilterData): List<CharacterData?>
    suspend fun getById(id: Int): CharacterData?
}