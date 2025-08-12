package com.gosty.data.api

import com.gosty.data.api.responses.MovieDetailResponse
import com.gosty.data.api.responses.MovieListsResponse
import com.gosty.data.api.responses.TVDetailResponse
import com.gosty.data.api.responses.TVListsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface ApiService {
    suspend fun fetchNowPlayingMovies(page: Int?): MovieListsResponse
    suspend fun fetchPopularMovies(page: Int?): MovieListsResponse
    suspend fun fetchTopRatedMovies(page: Int?): MovieListsResponse
    suspend fun fetchUpcomingMovies(page: Int?): MovieListsResponse
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse
    suspend fun fetchMovieRecommendations(movieId: Int, page: Int?): MovieListsResponse
    suspend fun searchMovies(query: String, page: Int?): MovieListsResponse
    suspend fun fetchTVAiringToday(page: Int?): TVListsResponse
    suspend fun fetchTVOnTheAir(page: Int?): TVListsResponse
    suspend fun fetchTVPopular(page: Int?): TVListsResponse
    suspend fun fetchTVTopRated(page: Int?): TVListsResponse
    suspend fun fetchTVDetails(tvId: Int): TVDetailResponse
    suspend fun fetchTVRecommendations(tvId: Int, page: Int?): TVListsResponse
    suspend fun searchTVShows(query: String, page: Int?): TVListsResponse
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun fetchNowPlayingMovies(page: Int?): MovieListsResponse {
        try {
            val response = client.get("movie/now_playing") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch now playing movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchPopularMovies(page: Int?): MovieListsResponse {
        try {
            val response = client.get("movie/popular") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch popular movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTopRatedMovies(page: Int?): MovieListsResponse {
        try {
            val response = client.get("movie/top_rated") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch top rated movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchUpcomingMovies(page: Int?): MovieListsResponse {
        try {
            val response = client.get("movie/upcoming") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch upcoming movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse {
        try {
            val response = client.get("movie/$movieId")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch movie details: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchMovieRecommendations(movieId: Int, page: Int?): MovieListsResponse {
        try {
            val response = client.get("movie/$movieId/recommendations") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch movie recommendations: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchMovies(
        query: String,
        page: Int?
    ): MovieListsResponse {
        try {
            val response = client.get("search/movie") {
                parameter("query", query)
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to search movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVAiringToday(page: Int?): TVListsResponse {
        try {
            val response = client.get("tv/airing_today") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV airing today: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVOnTheAir(page: Int?): TVListsResponse {
        try {
            val response = client.get("tv/on_the_air") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV on the air: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVPopular(page: Int?): TVListsResponse {
        try {
            val response = client.get("tv/popular") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch popular TV shows: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVTopRated(page: Int?): TVListsResponse {
        try {
            val response = client.get("tv/top_rated") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch top rated TV shows: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVDetails(tvId: Int): TVDetailResponse {
        try {
            val response = client.get("tv/$tvId")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV details: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVRecommendations(tvId: Int, page: Int?): TVListsResponse {
        try {
            val response = client.get("tv/$tvId/recommendations") {
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV recommendations: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchTVShows(
        query: String,
        page: Int?
    ): TVListsResponse {
        try {
            val response = client.get("search/tv") {
                parameter("query", query)
                page?.let { parameter("page", it) }
            }

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to search TV shows: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}