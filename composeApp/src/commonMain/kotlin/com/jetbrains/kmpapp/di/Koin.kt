package com.jetbrains.kmpapp.di

import com.gosty.data.di.dataPlatformModule
import com.gosty.data.di.databaseModule
import com.gosty.data.di.networkModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            networkModule,
            dataPlatformModule,
            databaseModule,
        )
    }
}
