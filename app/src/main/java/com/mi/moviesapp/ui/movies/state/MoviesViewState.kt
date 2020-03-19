package com.mi.moviesapp.ui.movies.state

import com.mi.moviesapp.data.model.Movie

data class MoviesViewState(
    var moviesRecyclerView: MoviesRecyclerView? = MoviesRecyclerView(),
    var movieDetailsFields: MovieDetailsFields? = MovieDetailsFields()
)

data class MoviesRecyclerView(
   var items : MutableList<Movie>? = null
)


data class MovieDetailsFields(
    var movie: Movie? = null
)