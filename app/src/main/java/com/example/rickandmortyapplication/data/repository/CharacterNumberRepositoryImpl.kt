package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.mapper.toDataMap
import com.example.rickandmortyapplication.data.mapper.toDomainMap
import com.example.rickandmortyapplication.data.storage.CharacterNumberStorage
import com.example.rickandmortyapplication.domain.model.NumberCharacter
import com.example.rickandmortyapplication.domain.repository.CharacterNumberRepository

class CharacterNumberRepositoryImpl(
   private val storage: CharacterNumberStorage
):CharacterNumberRepository  {
    override suspend fun saveNumber(numberChar: NumberCharacter) {
        storage.save(numberChar.toDataMap())
    }

    override suspend fun getNumber(): NumberCharacter {
        return storage.get().toDomainMap()
    }
}