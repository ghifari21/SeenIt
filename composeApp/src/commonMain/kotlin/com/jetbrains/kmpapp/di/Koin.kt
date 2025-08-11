package com.jetbrains.kmpapp.di

import com.gosty.common.constant.BaseUrl
import com.gosty.data.di.dataPlatformModule
import com.gosty.data.di.databaseModule
import com.gosty.data.sources.RemoteDataSource
import com.gosty.data.sources.RemoteDataSourceImpl
import com.gosty.seenit.config.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Application.Json)
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(accessToken = BuildConfig.API_KEY, refreshToken = "")
                    }
                }
            }
            defaultRequest {
                url(BaseUrl.API)
            }
        }
    }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val viewModelModule = module {}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            dataPlatformModule,
            databaseModule,
            viewModelModule,
        )
    }
}
