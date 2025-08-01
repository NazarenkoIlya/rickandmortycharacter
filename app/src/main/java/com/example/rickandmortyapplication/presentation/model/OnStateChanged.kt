package com.example.rickandmortyapplication.presentation.model

sealed interface OnStateChanged


object OnItemClicked : OnStateChanged
object OnFilterClicked: OnStateChanged