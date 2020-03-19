package com.mi.moviesapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id :Int,
    @ColumnInfo(name = "name")
    var name :String,
    @ColumnInfo(name = "image")
    var image :String,
    @ColumnInfo(name = "description")
    var description :String
)