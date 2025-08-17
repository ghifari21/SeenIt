package com.gosty.domain.usecases.movie

import com.gosty.common.models.Movie
import com.gosty.data.repositories.MovieRepository

class GetMovieRecommendationsPreviewUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): List<Movie> =
        movieRepository.getMovieRecommendationsPreview(movieId)
}