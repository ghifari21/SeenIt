package com.gosty.domain.usecases.movie

import com.gosty.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class IsMovieInWatchlistUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<Boolean> =
        movieRepository.isMovieInWatchlist(movieId)
}