package com.example.rickandmortyapplication.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapplication.data.model.room.CharacterDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(
            context,
            CharacterDataBase::class.java,
            "characters.db"
        ).fallbackToDestructiveMigration()
            .build()

    fun provideCharacterDao(db: CharacterDataBase) = db.characterDao()

    single { provideDataBase(androidContext()) }
    single { provideCharacterDao(get()) }
}