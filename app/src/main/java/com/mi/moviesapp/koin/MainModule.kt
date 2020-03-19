package com.mi.moviesapp.koin

import com.mi.moviesapp.data.network.movies.MoviesApiService
import com.mi.moviesapp.domain.movies.MoviesUseCase
import com.mi.moviesapp.ui.movies.MoviesActivity
import com.mi.moviesapp.ui.movies.MoviesViewModel
import com.mi.moviesapp.data.repository.movies.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val mainModule = module {
//    scope(named<MoviesActivity>()) {
        factory { provideMoviesAPI(get()) }
        factory { MoviesRepository(get(), get(), get()) }
        factory { MoviesUseCase(get()) }
        viewModel { MoviesViewModel(get()) }
//    }
}


fun provideMoviesAPI(retrofit: Retrofit): MoviesApiService =
        retrofit.create(MoviesApiService::class.java)

