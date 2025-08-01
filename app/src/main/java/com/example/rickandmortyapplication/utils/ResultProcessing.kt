package com.example.rickandmortyapplication.utils

sealed class ResultProcessing<out T> {
    data class Success<T>(val data: T): ResultProcessing<T>()
    data class Error(val message: String): ResultProcessing<Nothing>()
    object Loading : ResultProcessing<Nothing>()
}
