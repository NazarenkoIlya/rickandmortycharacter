package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.mapper.toDataMap
import com.example.rickandmortyapplication.data.mapper.toDomainMap
import com.example.rickandmortyapplication.data.storage.FilterStorage
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.FilterRepository

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage
) : FilterRepository {
    override suspend fun saveFilters(filter: FilterData) {
        filterStorage.save(filter.toDataMap())
    }

    override suspend fun getFilters(): FilterData {
        return filterStorage.get().toDomainMap()
    }
}