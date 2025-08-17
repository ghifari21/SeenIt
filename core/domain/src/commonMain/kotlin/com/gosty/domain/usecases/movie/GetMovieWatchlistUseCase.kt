package com.gosty.domain.usecases.movie

import androidx.paging.PagingData
import com.gosty.common.models.MovieWatchlist
import com.gosty.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieWatchlistUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<MovieWatchlist>> = movieRepository.getMovieWatchlist()
}