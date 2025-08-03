package com.example.rickandmortyapplication.data.model


data class DataResponse(
    val info: PageInfo,
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val image: String,
    val gender: String,
    val origin: Origin,
    val location: Location
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class Origin(
    val name: String
)

data class Location(
    val name: String
)