package com.mi.moviesapp.domain.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.mi.moviesapp.data.repository.movies.MoviesRepository
import com.mi.moviesapp.data.response_handler.DataState
import com.mi.moviesapp.ui.movies.state.MoviesViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class MoviesUseCase  constructor(val repository: MoviesRepository) {

    fun getMovies() : LiveData<DataState<MoviesViewState>> {
        return repository.getMovies().flowOn(Dispatchers.IO).asLiveData()
    }

    fun getMoviesDetails(id: Int): LiveData<DataState<MoviesViewState>> {
        return repository.getMoviesDetails(id).flowOn(Dispatchers.IO).asLiveData()
    }
}