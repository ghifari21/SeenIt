package com.gosty.seenit.di

import com.gosty.data.di.dataPlatformModule
import com.gosty.data.di.databaseModule
import com.gosty.data.di.networkModule
import com.gosty.data.di.repositoryModule
import com.gosty.domain.di.useCasesModule
import com.gosty.explore.di.exploreModule
import com.gosty.home.di.homeModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            networkModule,
            dataPlatformModule,
            databaseModule,
            repositoryModule,
            useCasesModule,
            homeModule,
            exploreModule
        )
    }
}
