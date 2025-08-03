package com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model

import android.graphics.Color
import java.util.Locale


sealed interface ViewItem {
    val id: String
}

data class CharacterForRecycle(
    override val id: String,
    val name: String,
    val image: Images,
    val status: String,
    val genderAndSpecies: String
) : ViewItem


fun CharacterForRecycle.statusColor(): Int {
    return when (status.lowercase(Locale.ROOT)) {
        "alive" -> Color.GREEN
        "dead" -> Color.RED
        else -> Color.GRAY
    }
}

data class Images(
    val url: String,
    val error: Int,
)






