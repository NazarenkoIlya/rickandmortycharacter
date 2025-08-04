package com.example.rickandmortyapplication.presentation.fragments.detailsfragment.model

sealed class UIDetailsState {
    object Loading: UIDetailsState()
    object Success: UIDetailsState()
    data class Error(val message: String): UIDetailsState()
}