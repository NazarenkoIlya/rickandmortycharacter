package com.example.rickandmortyapplication.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapplication.data.model.room.characters.dao.CharacterDao
import com.example.rickandmortyapplication.data.model.room.characters.dbo.CharacterDbo


@Database(entities = [CharacterDbo::class], version = 1)
abstract class CharacterDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}