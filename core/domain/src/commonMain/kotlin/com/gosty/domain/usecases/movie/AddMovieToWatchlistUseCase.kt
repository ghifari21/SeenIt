package com.gosty.domain.usecases.movie

import com.gosty.data.repositories.MovieRepository
import com.gosty.domain.models.Movie
import com.gosty.domain.models.MovieDetail
import com.gosty.domain.models.MovieWatchlist
import kotlinx.coroutines.flow.Flow

class AddMovieToWatchlistUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(movie: Movie): Flow<String> =
        movieRepository.addMovieToWatchlist(movie)

    operator fun invoke(movie: MovieDetail): Flow<String> =
        movieRepository.addMovieToWatchlist(movie)

    operator fun invoke(movie: MovieWatchlist): Flow<String> =
        movieRepository.addMovieToWatchlist(movie)
}