package com.example.rickandmortyapplication.data.mapper

import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.rickandmortyapplication.data.model.CharacterNumberData
import com.example.rickandmortyapplication.data.model.DataResponse
import com.example.rickandmortyapplication.data.model.FilterDataSave
import com.example.rickandmortyapplication.data.model.room.characters.dbo.CharacterDbo
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

fun FilterData.toQuery(): SupportSQLiteQuery {
    val base = StringBuilder("SELECT * FROM character WHERE 1=1")
    val args = mutableListOf<Any>()

    if (name.isNotBlank()) {
        base.append(" AND LOWER(name) LIKE ?")
        args.add("%${name.lowercase()}%")
    }
    if (status.isNotBlank()) {
        base.append(" AND LOWER(status) = ?")
        args.add(status.lowercase())
    }
    if (species.isNotBlank()) {
        base.append(" AND LOWER(species) = ?")
        args.add(species.lowercase())
    }
    if (type.isNotBlank()) {
        base.append(" AND LOWER(type) = ?")
        args.add(type.lowercase())
    }
    if (gender.isNotBlank()) {
        base.append(" AND LOWER(gender) = ?")
        args.add(gender.lowercase())
    }

    return SimpleSQLiteQuery(base.toString(), args.toTypedArray())
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
        isApply = isApply
    )
}

fun CharacterData.toCharacterDbo(): CharacterDbo {
    return CharacterDbo(
        id = id.toInt(),
        name = name,
        status = status,
        species = species,
        type = type,
        image = imageUrl,
        gender = gender,
        origin = origin,
        location = location
    )
}

fun CharacterDbo.toCharacterData(): CharacterData? {
    return CharacterData(
        id = id.toString(),
        name = name,
        status = status,
        species = species,
        type = type,
        imageUrl = image,
        gender = gender,
        origin = origin,
        location = location
    )
}