package com.gosty.seenit

import android.app.Application
import com.gosty.seenit.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SeenItApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(androidContext = this@SeenItApp)
        }
    }
}
