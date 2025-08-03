package com.example.rickandmortyapplication.data.storage

import com.example.rickandmortyapplication.data.model.FilterDataSave

interface FilterStorage {
    suspend fun save(filterData: FilterDataSave)
    suspend fun get(): FilterDataSave
}