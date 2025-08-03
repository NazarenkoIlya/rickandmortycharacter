package com.example.rickandmortyapplication.data.service

import com.example.rickandmortyapplication.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character/{num}")
    suspend fun getCharacter(
       @Path("num") num: Int
    ): Character
}