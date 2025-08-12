package com.gosty.data.di

import com.gosty.common.constant.BaseUrl
import com.gosty.data.api.ApiService
import com.gosty.data.api.ApiServiceImpl
import com.gosty.data.db.SeenItDatabase
import com.gosty.data.db.getRoomDatabase
import com.gosty.data.sources.remote.RemoteDataSource
import com.gosty.data.sources.remote.RemoteDataSourceImpl
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
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataPlatformModule: Module

val databaseModule = module {
    single<SeenItDatabase> { getRoomDatabase(get()) }
    single { get<SeenItDatabase>().movieWatchlistDao() }
    single { get<SeenItDatabase>().tvWatchlistDao() }
}

val networkModule = module {
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

    single<ApiService> { ApiServiceImpl(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}