package com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model

sealed interface Navigation

object GoToFilters: Navigation
object GoToDetail: Navigation