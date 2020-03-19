package com.mi.moviesapp.koin

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val mainModule = module {
//    scope(named<AuthActivity>()) {
//        factory { provideAuthAPI(get()) }
//        factory { AuthRepository(get(), get(), get(), get(), get(), get(), get()) }
//        factory { LoginUseCase(get()) }
//        factory { RegisterUseCase(get()) }
//        factory { CheckTokenUseCase(get()) }
//        viewModel { AuthViewModel(get(), get(), get()) }
//    }
}


//fun provideMoviesAPI(retrofit: Retrofit): AuthApiService =
//        retrofit.create(AuthApiService::class.java)
//
