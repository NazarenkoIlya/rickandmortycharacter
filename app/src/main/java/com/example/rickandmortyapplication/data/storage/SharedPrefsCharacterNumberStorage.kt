package com.example.rickandmortyapplication.data.storage

import android.content.Context
import androidx.core.content.edit
import com.example.rickandmortyapplication.data.model.CharacterNumberData


private const val SHARED_PREFS_NAME = "shared_prefs_number_character"
private const val KEY_NUMBER_CHARACTER = "key_number_character"

class SharedPrefsCharacterNumberStorage(
    context: Context
): CharacterNumberStorage{
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    override suspend fun save(numberCharacter: CharacterNumberData) {
        sharedPreferences.edit { putString(KEY_NUMBER_CHARACTER, numberCharacter.numberData.toString()) }
    }

    override suspend fun get(): CharacterNumberData {
        val number = sharedPreferences.getString(KEY_NUMBER_CHARACTER, null)?: "0"
        return CharacterNumberData(numberData = number.toInt())
    }
}
