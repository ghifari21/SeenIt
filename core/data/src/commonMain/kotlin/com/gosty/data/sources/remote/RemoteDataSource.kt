package com.gosty.data.sources.remote

import androidx.paging.PagingSource
import com.gosty.data.api.ApiService
import com.gosty.data.api.responses.MovieDetailResponse
import com.gosty.data.api.responses.MovieListsResult
import com.gosty.data.api.responses.TVDetailResponse
import com.gosty.data.api.responses.TVListsResult

interface RemoteDataSource {
    suspend fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchPopularMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse
    suspend fun fetchMovieRecommendations(movieId: Int): PagingSource<Int, MovieListsResult>
    suspend fun searchMovies(query: String): PagingSource<Int, MovieListsResult>
    suspend fun fetchTVAiringToday(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVPopular(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVTopRated(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVDetails(tvId: Int): TVDetailResponse
    suspend fun fetchTVRecommendations(tvId: Int): PagingSource<Int, TVListsResult>
    suspend fun searchTVShows(query: String): PagingSource<Int, TVListsResult>
}

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.NOW_PLAYING
        )
    }

    override suspend fun fetchPopularMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.POPULAR
        )
    }

    override suspend fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.TOP_RATED
        )
    }

    override suspend fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.UPCOMING
        )
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse {
        return apiService.fetchMovieDetails(movieId)
    }

    override suspend fun fetchMovieRecommendations(movieId: Int): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.RECOMMENDATIONS,
            id = movieId
        )
    }

    override suspend fun searchMovies(query: String): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.SEARCH,
            query = query
        )
    }

    override suspend fun fetchTVAiringToday(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.AIRING_TODAY
        )
    }

    override suspend fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.ON_THE_AIR
        )
    }

    override suspend fun fetchTVPopular(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.POPULAR
        )
    }

    override suspend fun fetchTVTopRated(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.TOP_RATED
        )
    }

    override suspend fun fetchTVDetails(tvId: Int): TVDetailResponse {
        return apiService.fetchTVDetails(tvId)
    }

    override suspend fun fetchTVRecommendations(tvId: Int): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.RECOMMENDATIONS,
            id = tvId
        )
    }

    override suspend fun searchTVShows(query: String): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.SEARCH,
            query = query
        )
    }
}