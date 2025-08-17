package com.gosty.domain.usecases.movie

import com.gosty.common.models.Movie
import com.gosty.common.models.MovieDetail
import com.gosty.common.models.MovieWatchlist
import com.gosty.data.repositories.MovieRepository

class RemoveMovieFromWatchlistUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) =
        movieRepository.removeMovieFromWatchlist(movie)

    suspend operator fun invoke(movie: MovieDetail) =
        movieRepository.removeMovieFromWatchlist(movie)

    suspend operator fun invoke(movie: MovieWatchlist) =
        movieRepository.removeMovieFromWatchlist(movie)
}