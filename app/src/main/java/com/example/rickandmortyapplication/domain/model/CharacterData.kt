package com.example.rickandmortyapplication.domain.model


data class CharacterResponse(
    val info: Info,
    val character: List<CharacterData>,
    val isApply: Boolean
)


data class Info(
    val next: String?,
    val prev: String?,
)

data class CharacterData(
    val id: String,
    val name: String,
    val imageUrl: String,
    val status: String,
    val gender: String,
    val type: String,
    val species: String,
    val origin: String,
    val location: String
)
