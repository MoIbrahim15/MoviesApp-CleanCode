package com.mi.moviesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mi.moviesapp.data.DataState
import com.mi.moviesapp.data.Response
import com.mi.moviesapp.data.ResponseView
abstract class BaseActivity : AppCompatActivity(),
    DataStateChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    override fun onDataStateChangeListener(dataState: DataState<*>?) {
        dataState?.let {
            displayLoading(it.loading.isLoading)
            it.error?.getContentIfNotHandled()?.let { error ->
                handleResponseState(error.response)
            }
            it.data?.response?.getContentIfNotHandled().let {response ->
                handleResponseState(response)
            }
        }
    }

    private fun handleResponseState(response: Response?) {
        response?.messageRes?.let {messageRes->
            val message = getString(messageRes)
            when (response.responseView) {
                is ResponseView.NONE -> {

                }
                is ResponseView.TOAST -> {
                    displayToast(message)
                }
            }
        }
    }

    abstract fun getLayoutRes(): Int
    abstract fun displayLoading(isLoading: Boolean)
}