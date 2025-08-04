package com.example.rickandmortyapplication.data.repository

import android.util.Log
import com.example.rickandmortyapplication.data.mapper.toCharacterData
import com.example.rickandmortyapplication.data.mapper.toCharacterDbo
import com.example.rickandmortyapplication.data.mapper.toQuery
import com.example.rickandmortyapplication.data.model.room.characters.dao.CharacterDao
import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository
import kotlin.math.log

class CharacterDataBaseRepositoryImpl(
    private val dao: CharacterDao
) : CharacterDataBaseRepository {
    override suspend fun insertAll(vararg characters: CharacterData) {

        val chars = characters.toList().map { it ->
            it.toCharacterDbo()
        }.toTypedArray()

        dao.insertAll(*chars)
    }

    override suspend fun getQuery(filter: FilterData): List<CharacterData?> {
        val query = filter.toQuery()
        return dao.getQuery(query).map { it ->
            it.toCharacterData()
        }
    }

    override suspend fun getById(id: Int): CharacterData? {
       return dao.getById(id)?.toCharacterData()
    }
}