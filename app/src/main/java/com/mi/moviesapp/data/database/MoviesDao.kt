package com.mi.moviesapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mi.moviesapp.data.model.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(response: MutableList<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(response: Movie)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovie(id: Int): Movie

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): MutableList<Movie>
}