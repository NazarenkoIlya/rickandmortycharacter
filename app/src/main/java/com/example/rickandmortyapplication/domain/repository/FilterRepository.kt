package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.domain.model.FilterData

interface FilterRepository {
    suspend fun saveFilters(filter: FilterData )
    suspend fun getFilters(): FilterData
}