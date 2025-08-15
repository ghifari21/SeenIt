package com.gosty.data.sources.remote

import androidx.paging.PagingSource
import com.gosty.data.api.ApiService
import com.gosty.data.api.responses.MovieDetailResponse
import com.gosty.data.api.responses.MovieListsResponse
import com.gosty.data.api.responses.MovieListsResult
import com.gosty.data.api.responses.TVDetailResponse
import com.gosty.data.api.responses.TVListsResponse
import com.gosty.data.api.responses.TVListsResult

interface RemoteDataSource {
    fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchNowPlayingMoviesPreview(): MovieListsResponse
    fun fetchPopularMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchPopularMoviesPreview(): MovieListsResponse
    fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchTopRatedMoviesPreview(): MovieListsResponse
    fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult>
    suspend fun fetchUpcomingMoviesPreview(): MovieListsResponse
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse
    fun fetchMovieRecommendations(movieId: Int): PagingSource<Int, MovieListsResult>
    suspend fun fetchMovieRecommendationsPreview(movieId: Int): MovieListsResponse
    fun searchMovies(query: String): PagingSource<Int, MovieListsResult>
    fun fetchTVAiringToday(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVAiringTodayPreview(): TVListsResponse
    fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVOnTheAirPreview(): TVListsResponse
    fun fetchTVPopular(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVPopularPreview(): TVListsResponse
    fun fetchTVTopRated(): PagingSource<Int, TVListsResult>
    suspend fun fetchTVTopRatedPreview(): TVListsResponse
    suspend fun fetchTVDetails(tvId: Int): TVDetailResponse
    fun fetchTVRecommendations(tvId: Int): PagingSource<Int, TVListsResult>
    suspend fun fetchTVRecommendationsPreview(tvId: Int): TVListsResponse
    fun searchTVShows(query: String): PagingSource<Int, TVListsResult>
}

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override fun fetchNowPlayingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.NOW_PLAYING
        )
    }

    override suspend fun fetchNowPlayingMoviesPreview(): MovieListsResponse {
        return apiService.fetchNowPlayingMovies(page = null)
    }

    override fun fetchPopularMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.POPULAR
        )
    }

    override suspend fun fetchPopularMoviesPreview(): MovieListsResponse {
        return apiService.fetchPopularMovies(page = null)
    }

    override fun fetchTopRatedMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.TOP_RATED
        )
    }

    override suspend fun fetchTopRatedMoviesPreview(): MovieListsResponse {
        return apiService.fetchTopRatedMovies(page = null)
    }

    override fun fetchUpcomingMovies(): PagingSource<Int, MovieListsResult> {
        return MoviePagingSource(
            apiService = apiService,
            movieType = MovieType.UPCOMING
        )
    }

    override suspend fun fetchUpcomingMoviesPreview(): MovieListsResponse {
        return apiService.fetchUpcomingMovies(page = null)
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

    override suspend fun fetchMovieRecommendationsPreview(movieId: Int): MovieListsResponse {
        return apiService.fetchMovieRecommendations(movieId = movieId, page = null)
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

    override suspend fun fetchTVAiringTodayPreview(): TVListsResponse {
        return apiService.fetchTVAiringToday(page = null)
    }

    override fun fetchTVOnTheAir(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.ON_THE_AIR
        )
    }

    override suspend fun fetchTVOnTheAirPreview(): TVListsResponse {
        return apiService.fetchTVOnTheAir(page = null)
    }

    override fun fetchTVPopular(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.POPULAR
        )
    }

    override suspend fun fetchTVPopularPreview(): TVListsResponse {
        return apiService.fetchTVPopular(page = null)
    }

    override fun fetchTVTopRated(): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.TOP_RATED
        )
    }

    override suspend fun fetchTVTopRatedPreview(): TVListsResponse {
        return apiService.fetchTVTopRated(page = null)
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

    override suspend fun fetchTVRecommendationsPreview(tvId: Int): TVListsResponse {
        return apiService.fetchTVRecommendations(tvId = tvId, page = null)
    }

    override fun searchTVShows(query: String): PagingSource<Int, TVListsResult> {
        return TVPagingSource(
            apiService = apiService,
            tvType = TVType.SEARCH,
            query = query
        )
    }
}