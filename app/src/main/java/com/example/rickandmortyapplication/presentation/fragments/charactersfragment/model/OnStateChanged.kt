package com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model

sealed interface OnStateChanged
data class OnItemClicked(val numberCharacter: Int) : OnStateChanged
object OnFilterClicked: OnStateChanged