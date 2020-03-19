package com.mi.moviesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mi.moviesapp.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): AuthMoviesDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}