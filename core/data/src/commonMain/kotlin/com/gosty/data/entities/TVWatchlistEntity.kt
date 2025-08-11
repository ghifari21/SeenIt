package com.gosty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_watchlist")
data class TVWatchlistEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
)