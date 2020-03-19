package com.mi.moviesapp.data.response_handler

import com.mi.moviesapp.R
import com.mi.moviesapp.data.response_handler.Response
import com.mi.moviesapp.data.response_handler.ResponseView
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler {
    fun <T> invoke(throwable: Throwable? = null, message: String? = null): DataState<T> {
        message?.let {
            return DataState.ERROR(
                Response(
                    getBusinessErrorRes(it),
                    ResponseView.DIALOG()
                )
            )
        }
        return when (throwable) {
            is IOException,
            is HttpException -> {
                DataState.ERROR(
                    Response(
                        R.string.error_something_went_wrong,
                        ResponseView.DIALOG()
                    )
                )
            }
            else -> DataState.ERROR(
                Response(
                    R.string.error_unknown,
                    ResponseView.DIALOG()
                )
            )
        }
    }

    private fun getBusinessErrorRes(message: String): Int {
        return when (message) {


            else -> R.string.error_something_went_wrong
        }
    }
}