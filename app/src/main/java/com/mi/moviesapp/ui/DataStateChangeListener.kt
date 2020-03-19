package com.mi.moviesapp.ui

import com.mi.moviesapp.data.response_handler.DataState


interface DataStateChangeListener {
    fun onDataStateChangeListener(dataState: DataState<*>?)
}