package com.gosty.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.gosty.data.db.dao.MovieWatchlistDao
import com.gosty.data.db.dao.TVWatchlistDao
import com.gosty.data.db.entities.MovieWatchlistEntity
import com.gosty.data.db.entities.TVWatchlistEntity

@Database(
    entities = [
        MovieWatchlistEntity::class,
        TVWatchlistEntity::class
    ],
    version = 1,
)
@ConstructedBy(SeenItDatabaseConstructor::class)
abstract class SeenItDatabase : RoomDatabase() {
    abstract fun movieWatchlistDao(): MovieWatchlistDao
    abstract fun tvWatchlistDao(): TVWatchlistDao
}

@Suppress("KotlinNoActualForExpect")
expect object SeenItDatabaseConstructor : RoomDatabaseConstructor<SeenItDatabase> {
    override fun initialize(): SeenItDatabase
}