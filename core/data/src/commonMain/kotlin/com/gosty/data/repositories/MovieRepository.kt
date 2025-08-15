package com.gosty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.common.utils.Result
import com.gosty.data.sources.local.LocalDataSource
import com.gosty.data.sources.remote.RemoteDataSource
import com.gosty.data.utils.toEntity
import com.gosty.data.utils.toModel
import com.gosty.domain.models.Movie
import com.gosty.domain.models.MovieDetail
import com.gosty.domain.models.MovieWatchlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    fun getNowPlayingMoviesPreview(): Flow<Result<List<Movie>>>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getPopularMoviesPreview(): Flow<Result<List<Movie>>>
    fun getTopRatedMovies(): Flow<PagingData<Movie>>
    fun getTopRatedMoviesPreview(): Flow<Result<List<Movie>>>
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
    fun getUpcomingMoviesPreview(): Flow<Result<List<Movie>>>
    fun getMovieDetails(movieId: Int): Flow<Result<MovieDetail>>
    fun getMovieRecommendations(movieId: Int): Flow<PagingData<Movie>>
    fun getMovieRecommendationsPreview(movieId: Int): Flow<Result<List<Movie>>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    fun addMovieToWatchlist(movie: Movie): Flow<String>
    fun addMovieToWatchlist(movie: MovieDetail): Flow<String>
    fun addMovieToWatchlist(movie: MovieWatchlist): Flow<String>
    fun removeMovieFromWatchlist(movie: Movie): Flow<String>
    fun removeMovieFromWatchlist(movie: MovieDetail): Flow<String>
    fun removeMovieFromWatchlist(movie: MovieWatchlist): Flow<String>
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
        }
    }

    override fun getNowPlayingMoviesPreview(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies =
                remoteDataSource.fetchNowPlayingMoviesPreview().results.map { it.toModel() }
            if (movies.isNotEmpty()) {
                emit(Result.Success(movies))
            } else {
                emit(Result.Empty)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
        }
    }

    override fun getPopularMoviesPreview(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies =
                remoteDataSource.fetchPopularMoviesPreview().results.map { it.toModel() }
            if (movies.isNotEmpty()) {
                emit(Result.Success(movies))
            } else {
                emit(Result.Empty)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
        }
    }

    override fun getTopRatedMoviesPreview(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies =
                remoteDataSource.fetchTopRatedMoviesPreview().results.map { it.toModel() }
            if (movies.isNotEmpty()) {
                emit(Result.Success(movies))
            } else {
                emit(Result.Empty)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
        }
    }

    override fun getUpcomingMoviesPreview(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies =
                remoteDataSource.fetchUpcomingMoviesPreview().results.map { it.toModel() }
            if (movies.isNotEmpty()) {
                emit(Result.Success(movies))
            } else {
                emit(Result.Empty)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Result<MovieDetail>> = flow {
        emit(Result.Loading)
        try {
            val movie = remoteDataSource.fetchMovieDetails(movieId).toModel()
            emit(Result.Success(movie))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

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
        }
    }

    override fun getMovieRecommendationsPreview(movieId: Int): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies =
                remoteDataSource.fetchMovieRecommendationsPreview(movieId).results.map { it.toModel() }
            if (movies.isNotEmpty()) {
                emit(Result.Success(movies))
            } else {
                emit(Result.Empty)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
        }
    }

    override fun addMovieToWatchlist(movie: Movie): Flow<String> = flow {
        try {
            val result = localDataSource.insertMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding movie to watchlist: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    override fun addMovieToWatchlist(movie: MovieDetail): Flow<String> = flow {
        try {
            val result = localDataSource.insertMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding movie to watchlist: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    override fun addMovieToWatchlist(movie: MovieWatchlist): Flow<String> = flow {
        try {
            val result = localDataSource.insertMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding movie to watchlist: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    override fun removeMovieFromWatchlist(movie: Movie): Flow<String> = flow {
        try {
            val result = localDataSource.deleteMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing movie from watchlist: ${e.message}")
        }
    }

    override fun removeMovieFromWatchlist(movie: MovieDetail): Flow<String> = flow {
        try {
            val result = localDataSource.deleteMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing movie from watchlist: ${e.message}")
        }
    }

    override fun removeMovieFromWatchlist(movie: MovieWatchlist): Flow<String> = flow {
        try {
            val result = localDataSource.deleteMovie(movie.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing movie from watchlist: ${e.message}")
        }
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
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }
    }

    override fun isMovieInWatchlist(movieId: Int): Flow<Boolean> = flow {
        try {
            val isInWatchlist = localDataSource.isMovieInWatchlist(movieId)
            emit(isInWatchlist)
        } catch (e: Exception) {
            emit(false)
        }
    }
}