package com.gosty.data.di

import com.gosty.data.db.getDatabaseBuilder
import org.koin.dsl.module

actual val dataPlatformModule = module {
    single { getDatabaseBuilder() }
}
