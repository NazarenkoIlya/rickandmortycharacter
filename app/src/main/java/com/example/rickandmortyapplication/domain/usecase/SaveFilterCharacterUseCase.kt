package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.FilterRepository

class SaveFilterCharacterUseCase(
    private val filterRepository: FilterRepository
) {
    suspend fun execute(filter: FilterData){
        filterRepository.saveFilters(filter)
    }
}