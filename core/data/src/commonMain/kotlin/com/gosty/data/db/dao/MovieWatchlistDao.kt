package com.gosty.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gosty.data.entities.MovieWatchlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieWatchlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieWatchlistEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieWatchlistEntity)

    @Query("SELECT * FROM movie_watchlist WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieWatchlistEntity?>

    @Query("SELECT * FROM movie_watchlist")
    fun getAllMovies(): Flow<List<MovieWatchlistEntity>>
}