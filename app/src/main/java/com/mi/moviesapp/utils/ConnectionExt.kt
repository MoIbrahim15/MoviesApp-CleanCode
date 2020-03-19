package com.mi.moviesapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build


fun Context.isConnectedToInternet(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    try {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities: NetworkCapabilities? = cm.getNetworkCapabilities(cm.activeNetwork)
            capabilities?.let {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                ) {
                    return true
                }
            }
        } else {
            try {
                val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
                activeNetworkInfo?.let {
                    return it.isConnected
                } ?: return false
            } catch (e: java.lang.Exception) {

            }
        }
    } catch (e: Exception) {

    }
    return false
}