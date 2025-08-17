package com.gosty.domain.usecases.movie

import androidx.paging.PagingData
import com.gosty.common.models.Movie
import com.gosty.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getPopularMovies()
}