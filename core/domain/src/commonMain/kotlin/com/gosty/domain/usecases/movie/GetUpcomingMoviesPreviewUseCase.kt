package com.gosty.domain.usecases.movie

import com.gosty.common.models.Movie
import com.gosty.data.repositories.MovieRepository

class GetUpcomingMoviesPreviewUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> = movieRepository.getUpcomingMoviesPreview()
}