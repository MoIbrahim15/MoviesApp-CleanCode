package com.mi.moviesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mi.moviesapp.data.DataState

abstract class BaseViewModel<EventState, ViewState> : ViewModel() {

    protected val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    protected val _eventState: MutableLiveData<EventState> = MutableLiveData()

    val viewState: LiveData<ViewState>
        get() = _viewState

    val dataState: LiveData<DataState<ViewState>> = Transformations
        .switchMap(_eventState) { _eventState ->
            handleEventState(_eventState)
        }

    fun setStateEvent(eventState: EventState) {
        _eventState.value = eventState
    }

    fun getCurrentViewStateOrNew(): ViewState {
        return this.viewState.value?.let {
            it
        } ?: initNewViewState()
    }

    abstract fun handleEventState(eventState: EventState): LiveData<DataState<ViewState>>

    abstract fun initNewViewState(): ViewState
}