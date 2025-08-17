package com.gosty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.common.models.Movie
import com.gosty.common.models.MovieDetail
import com.gosty.common.models.MovieWatchlist
import com.gosty.data.sources.local.LocalDataSource
import com.gosty.data.sources.remote.RemoteDataSource
import com.gosty.data.utils.toEntity
import com.gosty.data.utils.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    suspend fun getNowPlayingMoviesPreview(): List<Movie>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getPopularMoviesPreview(): List<Movie>
    fun getTopRatedMovies(): Flow<PagingData<Movie>>
    suspend fun getTopRatedMoviesPreview(): List<Movie>
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
    suspend fun getUpcomingMoviesPreview(): List<Movie>
    suspend fun getMovieDetails(movieId: Int): MovieDetail
    fun getMovieRecommendations(movieId: Int): Flow<PagingData<Movie>>
    suspend fun getMovieRecommendationsPreview(movieId: Int): List<Movie>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    suspend fun addMovieToWatchlist(movie: Movie)
    suspend fun addMovieToWatchlist(movie: MovieDetail)
    suspend fun addMovieToWatchlist(movie: MovieWatchlist)
    suspend fun removeMovieFromWatchlist(movie: Movie)
    suspend fun removeMovieFromWatchlist(movie: MovieDetail)
    suspend fun removeMovieFromWatchlist(movie: MovieWatchlist)
    fun getMovieWatchlist(): Flow<PagingData<MovieWatchlist>>
    fun isMovieInWatchlist(movieId: Int): Flow<Boolean>
}

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchNowPlayingMovies()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNowPlayingMoviesPreview(): List<Movie> {
        return remoteDataSource.fetchNowPlayingMoviesPreview().results.map { it.toModel() }
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchPopularMovies()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPopularMoviesPreview(): List<Movie> {
        return remoteDataSource.fetchPopularMoviesPreview().results.map { it.toModel() }
    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTopRatedMovies()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedMoviesPreview(): List<Movie> {
        return remoteDataSource.fetchTopRatedMoviesPreview().results.map { it.toModel() }
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchUpcomingMovies()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUpcomingMoviesPreview(): List<Movie> {
        return remoteDataSource.fetchUpcomingMoviesPreview().results.map { it.toModel() }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetail {
        return remoteDataSource.fetchMovieDetails(movieId).toModel()
    }

    override fun getMovieRecommendations(movieId: Int): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchMovieRecommendations(movieId)
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieRecommendationsPreview(movieId: Int): List<Movie> {
        return remoteDataSource.fetchMovieRecommendationsPreview(movieId).results.map { it.toModel() }
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.searchMovies(query)
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addMovieToWatchlist(movie: Movie) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override suspend fun addMovieToWatchlist(movie: MovieDetail) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override suspend fun addMovieToWatchlist(movie: MovieWatchlist) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override suspend fun removeMovieFromWatchlist(movie: Movie) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override suspend fun removeMovieFromWatchlist(movie: MovieDetail) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override suspend fun removeMovieFromWatchlist(movie: MovieWatchlist) {
        localDataSource.insertMovie(movie.toEntity())
    }

    override fun getMovieWatchlist(): Flow<PagingData<MovieWatchlist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                localDataSource.getAllMovies()
            }
        ).flow.map { entities ->
            entities.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override fun isMovieInWatchlist(movieId: Int): Flow<Boolean> = flow {
        try {
            val isInWatchlist = localDataSource.isMovieInWatchlist(movieId)
            emit(isInWatchlist)
        } catch (e: Exception) {
            emit(false)
        }
    }.flowOn(Dispatchers.IO)
}