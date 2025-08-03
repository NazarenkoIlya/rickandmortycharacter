package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.domain.model.NumberCharacter

interface CharacterNumberRepository {
    suspend fun saveNumber(numberChar: NumberCharacter)
    suspend fun getNumber(): NumberCharacter
}