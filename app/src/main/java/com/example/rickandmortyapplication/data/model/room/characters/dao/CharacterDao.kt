package com.example.rickandmortyapplication.data.model.room.characters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.rickandmortyapplication.data.model.room.characters.dbo.CharacterDbo


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg character: CharacterDbo)

    @RawQuery
    suspend fun getQuery(query: SupportSQLiteQuery): List<CharacterDbo>

    @Query("SELECT * FROM character WHERE status = :status and gender = :gender")
    suspend fun getQuery1(status: String, gender: String): List<CharacterDbo>

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getById(id: Int): CharacterDbo?
}