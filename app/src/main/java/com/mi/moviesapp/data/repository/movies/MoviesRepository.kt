package com.mi.moviesapp.data.repository.movies

import android.content.Context
import com.mi.moviesapp.data.database.MoviesDao
import com.mi.moviesapp.data.model.Movie
import com.mi.moviesapp.data.network.movies.MoviesApiService
import com.mi.moviesapp.data.repository.BaseRepository
import com.mi.moviesapp.data.repository.NetworkBoundResource
import com.mi.moviesapp.data.response_handler.DataState
import com.mi.moviesapp.data.response_handler.ErrorHandler
import com.mi.moviesapp.ui.movies.state.MovieDetailsFields
import com.mi.moviesapp.ui.movies.state.MoviesRecyclerView
import com.mi.moviesapp.ui.movies.state.MoviesViewState
import com.mi.moviesapp.utils.isConnectedToInternet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class MoviesRepository(
    private val moviesDao: MoviesDao,
    private val apiService: MoviesApiService,
    private val errorHandler: ErrorHandler,
    private val context: Context
) : BaseRepository() {

    fun getMovies(): Flow<DataState<MoviesViewState>> = flow {

        val networkBoundResource =
            object : NetworkBoundResource<MutableList<Movie>, MoviesViewState>(
                errorHandler,
                context.isConnectedToInternet(),
                true
            ) {
                override suspend fun createNetworkRequest(): MutableList<Movie> {
                    return apiService.getMovies()
                }

                override suspend fun handleSuccess(response: MutableList<Movie>) {

                    moviesDao.insertItems(response)

                    emit(
                        DataState.SUCCESS(
                            MoviesViewState(
                                moviesRecyclerView = MoviesRecyclerView(response)
                            )
                        )
                    )

                }

                override suspend fun createCacheRequest() {
                    moviesDao.getMovies()?.let { movies ->
                        emit(
                            DataState.SUCCESS(
                                MoviesViewState(
                                    moviesRecyclerView = MoviesRecyclerView(movies)
                                )
                            )
                        )

                    }
                }
            }
        emitAll(networkBoundResource.call())
    }

    fun getMoviesDetails(id: Int): Flow<DataState<MoviesViewState>> = flow {

        val networkBoundResource = object : NetworkBoundResource<Movie, MoviesViewState>(
            errorHandler,
            context.isConnectedToInternet(),
            true
        ) {
            override suspend fun createNetworkRequest(): Movie {
                return apiService.getMoviesDetails(id)
            }

            override suspend fun handleSuccess(response: Movie) {
                moviesDao.insertItem(response)

                emit(
                    DataState.SUCCESS(
                        MoviesViewState(
                            movieDetailsFields = MovieDetailsFields(response)
                        )
                    )
                )

            }

            override suspend fun createCacheRequest() {
                moviesDao.getMovie(id)?.let { movie ->
                    emit(
                        DataState.SUCCESS(
                            MoviesViewState(
                                movieDetailsFields = MovieDetailsFields(movie)
                            )
                        )
                    )

                }
            }
        }
        emitAll(networkBoundResource.call())
    }
}

