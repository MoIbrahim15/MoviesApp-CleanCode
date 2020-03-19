package com.mi.moviesapp.data.network.movies

import com.mi.moviesapp.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movies")
    suspend fun getMovies(): MutableList<Movie>

    @GET("movies/{movieID}")
    suspend fun getMoviesDetails(@Path("movieID")  movieID : Int): Movie

}