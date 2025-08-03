package com.example.rickandmortyapplication.di

import com.example.rickandmortyapplication.manager.ResourcesManager
import org.koin.dsl.module

val managerModule = module {
    single { ResourcesManager(get()) }
}