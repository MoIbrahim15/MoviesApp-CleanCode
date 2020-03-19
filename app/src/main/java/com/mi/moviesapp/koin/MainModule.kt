package com.mi.moviesapp.koin

import com.mi.moviesapp.data.network.movies.MoviesApiService
import com.mi.moviesapp.data.repository.movies.MoviesRepository
import com.mi.moviesapp.domain.movies.MoviesUseCase
import com.mi.moviesapp.ui.movies.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val mainModule = module {
//    scope(named<MoviesActivity>()) {
    factory { provideMoviesAPI(get()) }
    factory { MoviesRepository(get(), get(), get(), androidContext()) }
    factory { MoviesUseCase(get()) }
    viewModel { MoviesViewModel(get()) }
//    }
}


fun provideMoviesAPI(retrofit: Retrofit): MoviesApiService =
    retrofit.create(MoviesApiService::class.java)

