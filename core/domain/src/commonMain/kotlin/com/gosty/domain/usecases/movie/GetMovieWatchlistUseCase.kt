package com.gosty.domain.usecases.movie

import androidx.paging.PagingData
import com.gosty.data.repositories.MovieRepository
import com.gosty.domain.models.MovieWatchlist
import kotlinx.coroutines.flow.Flow

class GetMovieWatchlistUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<MovieWatchlist>> = movieRepository.getMovieWatchlist()
}