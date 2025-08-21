package com.gosty.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Entity(tableName = "movie_watchlist")
data class MovieWatchlistEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)