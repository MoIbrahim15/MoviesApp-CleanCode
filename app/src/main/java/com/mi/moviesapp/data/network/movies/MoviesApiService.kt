package com.mi.moviesapp.data.network.movies

import com.mi.moviesapp.data.model.Movie
import retrofit2.http.GET

interface MoviesApiService {

    @GET("movies")
    suspend fun getMovies(): MutableList<Movie>

}