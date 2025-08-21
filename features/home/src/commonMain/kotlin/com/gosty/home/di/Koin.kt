package com.gosty.home.di

import com.gosty.home.ui.screens.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    single {
        HomeViewModel(
            getPopularMoviesPreviewUseCase = get(),
            getNowPlayingMoviesPreviewUseCase = get(),
            getTopRatedMoviesPreviewUseCase = get(),
            getUpcomingMoviesPreviewUseCase = get(),
            getOnTheAirTVShowsPreviewUseCase = get(),
            getAiringTodayTVShowsPreviewUseCase = get(),
            getPopularTVShowsPreviewUseCase = get(),
            getTopRatedTVShowsPreviewUseCase = get()
        )
    }
}