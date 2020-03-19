package com.mi.moviesapp.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.displayToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.displayToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

