package com.example.rickandmortyapplication.manager

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes

class ResourcesManager(private val context: Context) {
    fun getString(@StringRes id: Int): String {
        return context.resources.getString(id)
    }

    fun getString(
        @StringRes id: Int,
        vararg @SuppressLint("SupportAnnotationUsage") @StringRes s: String,

        ): String {
        return context.resources.getString(id, *s)
    }

}