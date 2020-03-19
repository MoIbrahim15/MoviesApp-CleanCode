package com.mi.moviesapp.ui.movies.state

sealed class MoviesEventState {

    class GetMoviesEvent() : MoviesEventState()

    data class GetMovieDetailsEvent(
        var id: Int
    ) : MoviesEventState()

}