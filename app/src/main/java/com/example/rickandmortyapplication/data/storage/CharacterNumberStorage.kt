package com.example.rickandmortyapplication.data.storage

import com.example.rickandmortyapplication.data.model.CharacterNumberData

interface CharacterNumberStorage {
    suspend fun save(numberCharacter: CharacterNumberData)
    suspend fun get(): CharacterNumberData
}
