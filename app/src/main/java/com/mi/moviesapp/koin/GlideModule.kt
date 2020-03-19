package com.mi.moviesapp.koin

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mi.moviesapp.R
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val glideModule = module {
    single {
        RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }
    single {
        Glide.with(androidContext())
            .applyDefaultRequestOptions(get())
    }
}