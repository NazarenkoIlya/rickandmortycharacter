package com.example.rickandmortyapplication.data.model

data class FilterDataSave(
    val isApply: Boolean = false,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = ""
)
