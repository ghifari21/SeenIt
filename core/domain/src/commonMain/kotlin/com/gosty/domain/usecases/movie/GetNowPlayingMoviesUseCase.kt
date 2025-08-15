package com.gosty.domain.usecases.movie

import androidx.paging.PagingData
import com.gosty.data.repositories.MovieRepository
import com.gosty.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class GetNowPlayingMoviesUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getNowPlayingMovies()
}