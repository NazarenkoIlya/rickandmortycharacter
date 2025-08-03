package com.example.rickandmortyapplication.data.service

import com.example.rickandmortyapplication.data.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): DataResponse
}
