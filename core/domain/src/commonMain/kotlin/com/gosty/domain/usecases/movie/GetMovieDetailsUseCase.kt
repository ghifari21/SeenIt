package com.gosty.domain.usecases.movie

import com.gosty.common.models.MovieDetail
import com.gosty.data.repositories.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): MovieDetail =
        movieRepository.getMovieDetails(movieId)
}