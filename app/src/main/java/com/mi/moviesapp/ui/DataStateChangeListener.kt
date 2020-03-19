package com.mi.moviesapp.ui

import com.mi.moviesapp.data.DataState


interface DataStateChangeListener {
    fun onDataStateChangeListener(dataState: DataState<*>?)
}