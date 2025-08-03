package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.FilterRepository

class GetFilterCharacterUseCase(
    private val filterRepository: FilterRepository
) {
    suspend fun execute(): FilterData {
        return filterRepository.getFilters()
    }
}