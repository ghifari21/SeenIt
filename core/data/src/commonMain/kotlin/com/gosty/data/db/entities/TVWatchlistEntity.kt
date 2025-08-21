package com.gosty.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Entity(tableName = "tv_watchlist")
data class TVWatchlistEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)