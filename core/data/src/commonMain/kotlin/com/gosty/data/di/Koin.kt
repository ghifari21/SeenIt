package com.gosty.data.di

import com.gosty.data.db.SeenItDatabase
import com.gosty.data.db.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataPlatformModule: Module

val databaseModule = module {
    single<SeenItDatabase> { getRoomDatabase(get()) }
    single { get<SeenItDatabase>().movieWatchlistDao() }
    single { get<SeenItDatabase>().tvWatchlistDao() }
}