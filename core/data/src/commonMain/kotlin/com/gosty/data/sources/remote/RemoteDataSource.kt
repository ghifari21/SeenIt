package com.gosty.data.sources.remote

import androidx.paging.PagingSource
import com.gosty.data.api.ApiService
import com.gosty.data.api.responses.MovieDetailResponse
import com.gosty.data.api.responses.MovieListsResult
import com.gosty.data.api.responses.TVDetailResponse
import com.gosty.data.api.responses.TVListsResult

interface RemoteDataSource {
    fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult>
    fun fetchPopularMovies(): PagingSource<Int, MovieListsResult>
    fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult>
    fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse
    fun fetchMovieRecommendations(movieId: Int): PagingSource<Int, MovieListsResult>
    fun searchMovies(query: String): PagingSource<Int, MovieListsResult>
    fun fetchTVAiringToday(): PagingSource<Int, TVListsResult>
    fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult>
    fun fetchTVPopular(): PagingSource<Int, TVListsResult>
    fun fetchTVTopRated(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVDetails(tvId: Int): TVDetailResponse
    fun fetchTVRecommendations(tvId: Int): PagingSource<Int, TVListsResult>
    fun searchTVShows(query: String): PagingSource<Int, TVListsResult>
}

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.NOW_PLAYING
        )
    }

    override fun fetchPopularMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.POPULAR
        )
    }

    override fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.TOP_RATED
        )
    }

    override fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.UPCOMING
        )
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse {
        return apiService.fetchMovieDetails(movieId)
    }

    override fun fetchMovieRecommendations(movieId: Int): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.RECOMMENDATIONS,
            id = movieId
        )
    }

    override fun searchMovies(query: String): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.SEARCH,
            query = query
        )
    }

    override fun fetchTVAiringToday(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.AIRING_TODAY
        )
    }

    override fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.ON_THE_AIR
        )
    }

    override fun fetchTVPopular(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.POPULAR
        )
    }

    override fun fetchTVTopRated(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.TOP_RATED
        )
    }

    override suspend fun fetchTVDetails(tvId: Int): TVDetailResponse {
        return apiService.fetchTVDetails(tvId)
    }

    override fun fetchTVRecommendations(tvId: Int): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.RECOMMENDATIONS,
            id = tvId
        )
    }

    override fun searchTVShows(query: String): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.SEARCH,
            query = query
        )
    }
}