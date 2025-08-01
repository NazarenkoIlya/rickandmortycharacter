package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.data.service.CharacterService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun provideGson(): Gson = GsonBuilder()
        //.setLenient()
        .create()

    fun providePointsService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    single { providePointsService(get()) }
    factory { provideRetrofit(get()) }
    factory { provideGson() }
}