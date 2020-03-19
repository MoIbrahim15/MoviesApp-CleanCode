package com.mi.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import com.mi.moviesapp.data.response_handler.DataState
import com.mi.moviesapp.domain.movies.MoviesUseCase
import com.mi.moviesapp.ui.BaseViewModel
import com.mi.moviesapp.ui.movies.state.MoviesEventState
import com.mi.moviesapp.ui.movies.state.MoviesRecyclerView
import com.mi.moviesapp.ui.movies.state.MoviesViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MoviesViewModel(val useCase: MoviesUseCase) :BaseViewModel<MoviesEventState,MoviesViewState>() {

    override fun handleEventState(eventState: MoviesEventState): LiveData<DataState<MoviesViewState>> {
//        return when (eventState) {
//            is MoviesEventState.GetMoviesEvent -> {
//            }
//            is MoviesEventState.GetMovieDetailsEvent -> {
//                useCase.invoke(
//                    eventState.id,
//                )
//            }
//        }
   return useCase.invoke()

}

    override fun initNewViewState(): MoviesViewState {
        return MoviesViewState()
    }

    fun setMovies(moviesRecyclerView: MoviesRecyclerView?) {
        val update = getCurrentViewStateOrNew()
        if (update.moviesRecyclerView != moviesRecyclerView)
            update.moviesRecyclerView = moviesRecyclerView
        _viewState.value = update
    }
}