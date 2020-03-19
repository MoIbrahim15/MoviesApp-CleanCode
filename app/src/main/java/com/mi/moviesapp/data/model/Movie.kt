package com.mi.moviesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    var id :Int,
    var name :String,
    var image :String,
    var description :String
)