package com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model

sealed class UIState {
    object Loading: UIState()
    object Success: UIState()
    data class Error(val message: String): UIState()
}