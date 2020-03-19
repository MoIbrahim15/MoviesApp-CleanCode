package com.mi.moviesapp.data.response_handler

import com.mi.moviesapp.utils.SingleLiveData


data class Loading(val isLoading: Boolean)
data class Data<T>(val data: SingleLiveData<T>? = null, val response: SingleLiveData<Response>? = null)
data class Error(val response: Response? = null)


data class Response(
    val messageRes: Int,
    val responseView: ResponseView?
)

sealed class ResponseView {
    class TOAST : ResponseView()
    class DIALOG : ResponseView()
    class NONE : ResponseView()
}
