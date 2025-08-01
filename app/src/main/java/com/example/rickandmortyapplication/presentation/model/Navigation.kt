package com.example.rickandmortyapplication.presentation.model

sealed interface Navigation

object GoToFilters: Navigation
object GoToDetail: Navigation