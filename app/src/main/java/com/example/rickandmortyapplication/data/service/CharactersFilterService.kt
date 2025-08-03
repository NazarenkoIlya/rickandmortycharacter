package com.example.rickandmortyapplication.data.service

import com.example.rickandmortyapplication.data.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface CharactersFilterService {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @QueryMap filters: Map<String, String>
    ): DataResponse
}