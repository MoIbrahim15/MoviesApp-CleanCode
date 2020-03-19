package com.mi.moviesapp.data.repository

import com.mi.moviesapp.R
import com.mi.moviesapp.data.response_handler.DataState
import com.mi.moviesapp.data.response_handler.ErrorHandler
import com.mi.moviesapp.data.response_handler.Response
import com.mi.moviesapp.data.response_handler.ResponseView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResponseObject, ViewStateType>(
    private val errorHandler: ErrorHandler,
    private val isNetworkAvailable: Boolean,
    private val isNetworkRequest: Boolean
) {

    fun call(): Flow<DataState<ViewStateType>> = flow {
        emit(DataState.LOADING(isLoading = true))

        if (isNetworkAvailable) {
            if (isNetworkAvailable) {
                try {
                    val apiResponse = createNetworkRequest()
                    apiResponse?.let {
                            handleSuccess(apiResponse)
                    }
                } catch (exception: Exception) {
                    emit(errorHandler.invoke(throwable = exception))
                }
            } else {
                emit(
                    DataState.ERROR<ViewStateType>(
                        Response(
                            R.string.error_no_internet_connection,
                            ResponseView.DIALOG()
                        )
                    )
                )
            }
        } else {
            createCacheRequest()
        }
    }


    abstract suspend fun createNetworkRequest(): ResponseObject?
    abstract suspend fun createCacheRequest()
    abstract suspend fun handleSuccess(response: ResponseObject)
}