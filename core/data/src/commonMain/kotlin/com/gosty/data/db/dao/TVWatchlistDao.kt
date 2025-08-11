package com.gosty.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gosty.data.entities.TVWatchlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TVWatchlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTV(tv: TVWatchlistEntity)

    @Delete
    suspend fun deleteTV(tv: TVWatchlistEntity)

    @Query("SELECT * FROM tv_watchlist WHERE id = :id")
    fun getTVById(id: Int): Flow<TVWatchlistEntity?>

    @Query("SELECT * FROM tv_watchlist")
    fun getAllTVs(): Flow<List<TVWatchlistEntity>>
}