package com.example.rickandmortyapplication.data.model.room.characters.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "character")
data class CharacterDbo(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val image: String,
    val gender: String,
    val origin: String,
    val location: String
)
