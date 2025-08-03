package com.example.rickandmortyapplication.domain.model



data class FilterData(
    val isApply: Boolean,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String
)
