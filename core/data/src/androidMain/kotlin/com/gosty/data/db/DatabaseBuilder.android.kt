package com.gosty.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<SeenItDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("seen_it.db")
    return Room.databaseBuilder<SeenItDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}