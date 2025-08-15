package com.gosty.domain.usecases.movie

import com.gosty.common.utils.Result
import com.gosty.data.repositories.MovieRepository
import com.gosty.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class GetNowPlayingMoviesPreviewUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<Result<List<Movie>>> = movieRepository.getNowPlayingMoviesPreview()
}