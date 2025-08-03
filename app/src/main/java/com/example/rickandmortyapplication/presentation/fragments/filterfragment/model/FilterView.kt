package com.example.rickandmortyapplication.presentation.fragments.filterfragment.model

data class FilterView(
    val isApply: Boolean,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender
)


enum class Status(val status: String){
    None(""),
    Alive("alive"),
    Dead("dead"),
    Unknown("unknown"),
}

enum class Gender(val gender: String){
    None(""),
    Female("female"),
    Male("male"),
    Genderless("genderless"),
    Unknown("unknown")
}
