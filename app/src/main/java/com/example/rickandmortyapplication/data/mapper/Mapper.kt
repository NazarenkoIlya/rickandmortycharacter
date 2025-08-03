package com.example.rickandmortyapplication.data.mapper

import com.example.rickandmortyapplication.data.model.CharacterNumberData
import com.example.rickandmortyapplication.data.model.DataResponse
import com.example.rickandmortyapplication.data.model.FilterDataSave
import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.model.Info
import com.example.rickandmortyapplication.domain.model.NumberCharacter

fun CharacterNumberData.toDomainMap() = NumberCharacter(
    number = numberData
)

fun NumberCharacter.toDataMap() = CharacterNumberData(
    numberData = number
)

fun FilterDataSave.toDomainMap() = FilterData(
    isApply = isApply,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender
)

fun FilterData.toDataMap() = FilterDataSave(
    isApply = isApply,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender
)

fun FilterData.toQueryMap(): Map<String, String> {
    return buildMap {
        if (name.isNotBlank()) put("name", name)
        if (status.isNotBlank()) put("status", status)
        if (species.isNotBlank()) put("species", species)
        if (type.isNotBlank()) put("type", type)
        if (gender.isNotBlank()) put("gender", gender)
    }
}


fun DataResponse.toCharacterResponse(isApply: Boolean): CharacterResponse {

    val characterData = results.map { it ->
        CharacterData(
            id = it.id.toString(),
            name = it.name,
            imageUrl = it.image,
            status = it.status,
            gender = it.gender,
            type = it.type,
            species = it.species,
            origin = it.origin.name,
            location = it.location.name,
        )
    }
    val info = Info(
        next = info.next,
        prev = info.prev,
    )
    return CharacterResponse(
        info = info,
        character = characterData,
        isApply=isApply
    )
}