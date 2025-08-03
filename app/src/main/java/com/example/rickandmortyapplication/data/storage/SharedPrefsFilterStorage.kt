package com.example.rickandmortyapplication.data.storage

import android.content.Context
import androidx.core.content.edit
import com.example.rickandmortyapplication.data.model.CharacterNumberData
import com.example.rickandmortyapplication.data.model.FilterDataSave
import com.google.gson.Gson


private const val SHARED_PREFS_NAME = "shared_prefs_filters"
private const val KEY_FILTERS = "key_filters"

class SharedPrefsFilterStorage(
    context: Context,
    val gson: Gson,
) : FilterStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun save(filterData: FilterDataSave) {
        sharedPreferences.edit { putString(KEY_FILTERS, gson.toJson(filterData)) }
    }

    override suspend fun get(): FilterDataSave {
        return gson.fromJson(
            sharedPreferences.getString(KEY_FILTERS, null),
            FilterDataSave::class.java
        ) ?: FilterDataSave()
    }
}

