package com.gosty.data.sources

import com.gosty.data.db.dao.MovieWatchlistDao
import com.gosty.data.db.dao.TVWatchlistDao
import com.gosty.data.entities.MovieWatchlistEntity
import com.gosty.data.entities.TVWatchlistEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

interface LocalDataSource {
    suspend fun insertMovie(movie: MovieWatchlistEntity)
    suspend fun deleteMovie(movie: MovieWatchlistEntity)
    fun getMovieById(id: Int): Flow<MovieWatchlistEntity?>
    fun getAllMovies(): Flow<List<MovieWatchlistEntity>>
    suspend fun isMovieInWatchlist(id: Int): Boolean
    suspend fun insertTV(tv: TVWatchlistEntity)
    suspend fun deleteTV(tv: TVWatchlistEntity)
    fun getTVById(id: Int): Flow<TVWatchlistEntity?>
    fun getAllTVShows(): Flow<List<TVWatchlistEntity>>
    suspend fun isTVInWatchlist(id: Int): Boolean
}

class LocalDataSourceImpl(
    private val movieWatchlistDao: MovieWatchlistDao,
    private val tvWatchlistDao: TVWatchlistDao
) : LocalDataSource {
    override suspend fun insertMovie(movie: MovieWatchlistEntity) {
        movieWatchlistDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: MovieWatchlistEntity) {
        movieWatchlistDao.deleteMovie(movie)
    }

    override fun getMovieById(id: Int): Flow<MovieWatchlistEntity?> {
        return movieWatchlistDao.getMovieById(id)
    }

    override fun getAllMovies(): Flow<List<MovieWatchlistEntity>> {
        return movieWatchlistDao.getAllMovies()
    }

    override suspend fun isMovieInWatchlist(id: Int): Boolean {
        return getMovieById(id).firstOrNull() != null
    }

    override suspend fun insertTV(tv: TVWatchlistEntity) {
        tvWatchlistDao.insertTV(tv)
    }

    override suspend fun deleteTV(tv: TVWatchlistEntity) {
        tvWatchlistDao.deleteTV(tv)
    }

    override fun getTVById(id: Int): Flow<TVWatchlistEntity?> {
        return tvWatchlistDao.getTVById(id)
    }

    override fun getAllTVShows(): Flow<List<TVWatchlistEntity>> {
        return tvWatchlistDao.getAllTVs()
    }

    override suspend fun isTVInWatchlist(id: Int): Boolean {
        return getTVById(id).firstOrNull() != null
    }

}