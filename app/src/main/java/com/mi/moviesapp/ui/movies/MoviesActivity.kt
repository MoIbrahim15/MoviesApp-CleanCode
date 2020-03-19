package com.mi.moviesapp.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import com.mi.moviesapp.R
import com.mi.moviesapp.ui.BaseActivity
import com.mi.moviesapp.ui.displayToast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MoviesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun displayLoading(isLoading: Boolean) {

    }
}