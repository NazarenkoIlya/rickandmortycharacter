package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.mapper.toCharacterResponse
import com.example.rickandmortyapplication.data.mapper.toQueryMap
import com.example.rickandmortyapplication.data.service.CharactersFilterService
import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.repository.FilterCharacterRepository

class FilterCharacterRepositoryImpl(
    private val charactersFilterService: CharactersFilterService
) : FilterCharacterRepository {
    override suspend fun getCharacters(
        page: Int,
        filter: FilterData
    ): CharacterResponse {
        val filterMap = filter.toQueryMap()
        return charactersFilterService.getCharacters(page, filterMap)
            .toCharacterResponse(filter.isApply)
    }
}
