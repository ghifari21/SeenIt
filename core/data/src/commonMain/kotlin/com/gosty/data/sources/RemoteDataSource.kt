package com.gosty.data.sources

import com.gosty.data.responses.MovieDetailResponse
import com.gosty.data.responses.MovieListsResponse
import com.gosty.data.responses.TVDetailResponse
import com.gosty.data.responses.TVListsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface RemoteDataSource {
    suspend fun fetchNowPlayingMovies(): MovieListsResponse
    suspend fun fetchPopularMovies(): MovieListsResponse
    suspend fun fetchTopRatedMovies(): MovieListsResponse
    suspend fun fetchUpcomingMovies(): MovieListsResponse
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailResponse
    suspend fun fetchMovieRecommendations(movieId: Int): MovieListsResponse
    suspend fun searchMovies(query: String): MovieListsResponse
    suspend fun fetchTVAiringToday(): TVListsResponse
    suspend fun fetchTVOnTheAir(): TVListsResponse
    suspend fun fetchTVPopular(): TVListsResponse
    suspend fun fetchTVTopRated(): TVListsResponse
    suspend fun fetchTVDetails(tvId: Int): TVDetailResponse
    suspend fun fetchTVRecommendations(tvId: Int): TVListsResponse
    suspend fun searchTVShows(query: String): TVListsResponse
}

class RemoteDataSourceImpl(private val client: HttpClient) : RemoteDataSource {
    override suspend fun fetchNowPlayingMovies(): MovieListsResponse {
        try {
            val response = client.get("movie/now_playing")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch now playing movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchPopularMovies(): MovieListsResponse {
        try {
            val response = client.get("movie/popular")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch popular movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTopRatedMovies(): MovieListsResponse {
        try {
            val response = client.get("movie/top_rated")
            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch top rated movies: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchUpcomingMovies(): MovieListsResponse {
        try {
            val response = client.get("movie/upcoming")

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

    override suspend fun fetchMovieRecommendations(movieId: Int): MovieListsResponse {
        try {
            val response = client.get("movie/$movieId/recommendations")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch movie recommendations: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchMovies(query: String): MovieListsResponse {
        try {
            val response = client.get("search/movie") {
                parameter("query", query)
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

    override suspend fun fetchTVAiringToday(): TVListsResponse {
        try {
            val response = client.get("tv/airing_today")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV airing today: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVOnTheAir(): TVListsResponse {
        try {
            val response = client.get("tv/on_the_air")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV on the air: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVPopular(): TVListsResponse {
        try {
            val response = client.get("tv/popular")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch popular TV shows: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchTVTopRated(): TVListsResponse {
        try {
            val response = client.get("tv/top_rated")

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

    override suspend fun fetchTVRecommendations(tvId: Int): TVListsResponse {
        try {
            val response = client.get("tv/$tvId/recommendations")

            if (response.status.value == 200) {
                return response.body()
            } else {
                throw Exception("Failed to fetch TV recommendations: ${response.status}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchTVShows(query: String): TVListsResponse {
        try {
            val response = client.get("search/tv") {
                parameter("query", query)
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