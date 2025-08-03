package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.domain.model.CharacterData

interface CharacterRepository {
    suspend fun getCharacter(number: Int): CharacterData
}