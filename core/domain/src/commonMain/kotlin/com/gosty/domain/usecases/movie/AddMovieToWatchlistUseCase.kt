package com.gosty.domain.usecases.movie

import com.gosty.common.models.Movie
import com.gosty.common.models.MovieDetail
import com.gosty.common.models.MovieWatchlist
import com.gosty.data.repositories.MovieRepository

class AddMovieToWatchlistUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) =
        movieRepository.addMovieToWatchlist(movie)

    suspend operator fun invoke(movie: MovieDetail) =
        movieRepository.addMovieToWatchlist(movie)

    suspend operator fun invoke(movie: MovieWatchlist) =
        movieRepository.addMovieToWatchlist(movie)
}