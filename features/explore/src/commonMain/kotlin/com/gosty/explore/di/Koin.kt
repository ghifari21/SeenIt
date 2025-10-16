package com.gosty.explore.di

import com.gosty.explore.ui.screens.ExploreViewModel
import org.koin.dsl.module

val exploreModule = module {
    single {
        ExploreViewModel(
            savedStateHandle = get(),
            getNowPlayingMoviesUseCase = get(),
            getPopularMoviesUseCase = get(),
            getTopRatedMoviesUseCase = get(),
            getUpcomingMoviesUseCase = get(),
            getAiringTodayTVShowsUseCase = get(),
            getOnTheAirTVShowsUseCase = get(),
            getPopularTVShowsUseCase = get(),
            getTopRatedTVShowsUseCase = get()
        )
    }
}