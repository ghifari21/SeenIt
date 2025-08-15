package com.gosty.domain.di

import com.gosty.domain.usecases.movie.AddMovieToWatchlistUseCase
import com.gosty.domain.usecases.movie.GetMovieDetailsUseCase
import com.gosty.domain.usecases.movie.GetMovieRecommendationsPreviewUseCase
import com.gosty.domain.usecases.movie.GetMovieRecommendationsUseCase
import com.gosty.domain.usecases.movie.GetMovieWatchlistUseCase
import com.gosty.domain.usecases.movie.GetNowPlayingMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetNowPlayingMoviesUseCase
import com.gosty.domain.usecases.movie.GetPopularMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetPopularMoviesUseCase
import com.gosty.domain.usecases.movie.GetTopRatedMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetTopRatedMoviesUseCase
import com.gosty.domain.usecases.movie.GetUpcomingMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetUpcomingMoviesUseCase
import com.gosty.domain.usecases.movie.IsMovieInWatchlistUseCase
import com.gosty.domain.usecases.movie.RemoveMovieFromWatchlistUseCase
import com.gosty.domain.usecases.movie.SearchMoviesUseCase
import com.gosty.domain.usecases.tv.AddTVToWatchlistUseCase
import com.gosty.domain.usecases.tv.GetAiringTodayTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetAiringTodayTVShowsUseCase
import com.gosty.domain.usecases.tv.GetOnTheAirTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetOnTheAirTVShowsUseCase
import com.gosty.domain.usecases.tv.GetPopularTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetPopularTVShowsUseCase
import com.gosty.domain.usecases.tv.GetTVDetailsUseCase
import com.gosty.domain.usecases.tv.GetTVRecommendationsPreviewUseCase
import com.gosty.domain.usecases.tv.GetTVRecommendationsUseCase
import com.gosty.domain.usecases.tv.GetTVWatchlistUseCase
import com.gosty.domain.usecases.tv.GetTopRatedTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetTopRatedTVShowsUseCase
import com.gosty.domain.usecases.tv.IsTVInWatchlistUseCase
import com.gosty.domain.usecases.tv.RemoveTVFromWatchlistUseCase
import com.gosty.domain.usecases.tv.SearchTVShowsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    // movies
    single { AddMovieToWatchlistUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { GetMovieRecommendationsUseCase(get()) }
    single { GetMovieRecommendationsPreviewUseCase(get()) }
    single { GetMovieWatchlistUseCase(get()) }
    single { GetNowPlayingMoviesUseCase(get()) }
    single { GetNowPlayingMoviesPreviewUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetPopularMoviesPreviewUseCase(get()) }
    single { GetTopRatedMoviesUseCase(get()) }
    single { GetTopRatedMoviesPreviewUseCase(get()) }
    single { GetUpcomingMoviesUseCase(get()) }
    single { GetUpcomingMoviesPreviewUseCase(get()) }
    single { IsMovieInWatchlistUseCase(get()) }
    single { RemoveMovieFromWatchlistUseCase(get()) }
    single { SearchMoviesUseCase(get()) }

    // tv shows
    single { AddTVToWatchlistUseCase(get()) }
    single { GetAiringTodayTVShowsUseCase(get()) }
    single { GetAiringTodayTVShowsPreviewUseCase(get()) }
    single { GetOnTheAirTVShowsUseCase(get()) }
    single { GetOnTheAirTVShowsPreviewUseCase(get()) }
    single { GetPopularTVShowsUseCase(get()) }
    single { GetPopularTVShowsPreviewUseCase(get()) }
    single { GetTopRatedTVShowsUseCase(get()) }
    single { GetTopRatedTVShowsPreviewUseCase(get()) }
    single { GetTVDetailsUseCase(get()) }
    single { GetTVRecommendationsUseCase(get()) }
    single { GetTVRecommendationsPreviewUseCase(get()) }
    single { GetTVWatchlistUseCase(get()) }
    single { IsTVInWatchlistUseCase(get()) }
    single { RemoveTVFromWatchlistUseCase(get()) }
    single { SearchTVShowsUseCase(get()) }
}