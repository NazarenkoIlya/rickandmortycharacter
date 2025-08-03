package com.example.rickandmortyapplication.app

import android.app.Application
import com.example.rickandmortyapplication.di.dataModule
import com.example.rickandmortyapplication.di.domainModule
import com.example.rickandmortyapplication.di.managerModule
import com.example.rickandmortyapplication.di.retrofitModule
import com.example.rickandmortyapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                listOf(
                    retrofitModule,
                    //gsonModule,
                    //serviceModule,
                    viewModelModule,
                    managerModule,
                    dataModule,
                    domainModule,
                    //dataBaseModule
                )
            )
        }
    }
}