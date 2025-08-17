package com.gosty.domain.usecases.movie

import androidx.paging.PagingData
import com.gosty.common.models.Movie
import com.gosty.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieRecommendationsUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<PagingData<Movie>> =
        movieRepository.getMovieRecommendations(movieId)
}