package com.example.rickandmortyapplication.presentation.fragments.filterfragment.model

sealed interface OnStateChanged

object OnResetClicked : OnStateChanged
object OnApplyClicked : OnStateChanged
data class RadioButtonStatusChanged(
    val status: String
) : OnStateChanged

data class RadioButtonGenderChanged(
    val gender: String
) : OnStateChanged

data class NameChanged(
    val name: String
) : OnStateChanged

data class TypeChanged(
    val type: String
) : OnStateChanged

data class SpeciesChanged(
    val species: String
) : OnStateChanged
