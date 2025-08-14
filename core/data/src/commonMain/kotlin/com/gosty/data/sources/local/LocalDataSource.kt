package com.gosty.data.sources.local

import androidx.paging.PagingSource
import com.gosty.data.db.dao.MovieWatchlistDao
import com.gosty.data.db.dao.TVWatchlistDao
import com.gosty.data.db.entities.MovieWatchlistEntity
import com.gosty.data.db.entities.TVWatchlistEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

interface LocalDataSource {
    suspend fun insertMovie(movie: MovieWatchlistEntity): String
    suspend fun deleteMovie(movie: MovieWatchlistEntity): String
    fun getMovieById(id: Int): Flow<MovieWatchlistEntity?>
    fun getAllMovies(): PagingSource<Int, MovieWatchlistEntity>
    suspend fun isMovieInWatchlist(id: Int): Boolean
    suspend fun insertTV(tv: TVWatchlistEntity): String
    suspend fun deleteTV(tv: TVWatchlistEntity): String
    fun getTVById(id: Int): Flow<TVWatchlistEntity?>
    fun getAllTVShows(): PagingSource<Int, TVWatchlistEntity>
    suspend fun isTVInWatchlist(id: Int): Boolean
}

class LocalDataSourceImpl(
    private val movieWatchlistDao: MovieWatchlistDao,
    private val tvWatchlistDao: TVWatchlistDao
) : LocalDataSource {
    override suspend fun insertMovie(movie: MovieWatchlistEntity): String {
        movieWatchlistDao.insertMovie(movie)
        return "Movie added to watchlist"
    }

    override suspend fun deleteMovie(movie: MovieWatchlistEntity): String {
        movieWatchlistDao.deleteMovie(movie)
        return "Movie removed from watchlist"
    }

    override fun getMovieById(id: Int): Flow<MovieWatchlistEntity?> {
        return movieWatchlistDao.getMovieById(id)
    }

    override fun getAllMovies(): PagingSource<Int, MovieWatchlistEntity> {
        return MovieWatchlistPagingSource(movieWatchlistDao)
    }

    override suspend fun isMovieInWatchlist(id: Int): Boolean {
        return getMovieById(id).firstOrNull() != null
    }

    override suspend fun insertTV(tv: TVWatchlistEntity): String {
        tvWatchlistDao.insertTV(tv)
        return "TV show added to watchlist"
    }

    override suspend fun deleteTV(tv: TVWatchlistEntity): String {
        tvWatchlistDao.deleteTV(tv)
        return "TV show removed from watchlist"
    }

    override fun getTVById(id: Int): Flow<TVWatchlistEntity?> {
        return tvWatchlistDao.getTVById(id)
    }

    override fun getAllTVShows(): PagingSource<Int, TVWatchlistEntity> {
        return TVWatchlistPagingSource(tvWatchlistDao)
    }

    override suspend fun isTVInWatchlist(id: Int): Boolean {
        return getTVById(id).firstOrNull() != null
    }
}