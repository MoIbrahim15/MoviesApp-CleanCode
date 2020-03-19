package com.mi.moviesapp.data.repository.movies

import com.mi.moviesapp.data.database.MoviesDao
import com.mi.moviesapp.data.model.Movie
import com.mi.moviesapp.data.network.movies.MoviesApiService
import com.mi.moviesapp.data.response_handler.DataState
import com.mi.moviesapp.data.response_handler.ErrorHandler
import com.mi.moviesapp.ui.movies.state.MoviesRecyclerView
import com.mi.moviesapp.ui.movies.state.MoviesViewState
import com.mi.moviesapp.data.repository.BaseRepository
import com.mi.moviesapp.data.repository.NetworkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class MoviesRepository(
    private val moviesDao: MoviesDao,
    private val apiService: MoviesApiService,
    private val errorHandler: ErrorHandler
) : BaseRepository() {

    fun getMovies(): Flow<DataState<MoviesViewState>> = flow {

        val networkBoundResource = object : NetworkBoundResource<MutableList<Movie>, MoviesViewState>(
            errorHandler,
            true,
            true
        ) {
            override suspend fun createNetworkRequest(): MutableList<Movie> {
                return apiService.getMovies()
            }

            override suspend fun handleSuccess(response: MutableList<Movie>) {
//                    moviesDao.insertOrIgnore(Account(response.pk ))
//                    val result = moviesDao.insert(AuthToken(response.pk))
//                    if (result < 0) {
//                        emit(
//                            DataState.ERROR(
//                                Response(
//                                    R.string.error_something_went_wrong,
//                                    ResponseView.DIALOG()
//                                )
//                            )
//                        )
//                    } else {
                emit(
                    DataState.SUCCESS(
                        MoviesViewState(
                            moviesRecyclerView = MoviesRecyclerView(response)
                        )
                    )
                )

//                    }
            }

            override suspend fun createCacheRequest() {

            }
        }
        emitAll(networkBoundResource.call())
    }
}

