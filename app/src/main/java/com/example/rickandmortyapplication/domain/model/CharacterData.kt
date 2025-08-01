package com.example.rickandmortyapplication.domain.model


data class CharacterData(
    val id: String,
    val name: String,
    val imageUrl: String,
    val status: String,
    val gender: String,
    val species: String,
    val origin: String,
    val location: String
)
