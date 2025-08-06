package com.jetbrains.kmpapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.module

val dataModule = module {
}

val viewModelModule = module {}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
