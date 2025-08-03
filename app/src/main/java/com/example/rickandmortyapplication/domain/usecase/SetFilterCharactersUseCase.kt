package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.FilterCharacterRepository
import com.example.rickandmortyapplication.domain.repository.FilterRepository

class SetFilterCharactersUseCase(
    private val filterCharacterRepository: FilterCharacterRepository,
    private val filterRepository: FilterRepository
) {
    suspend fun execute(page: Int, filter: FilterData): CharacterResponse {
        val filterApply = filter
        val filterNew = filterApply.copy(isApply = false)
        filterRepository.saveFilters(filterNew)
        return filterCharacterRepository.getCharacters(page, filterApply)
    }
}