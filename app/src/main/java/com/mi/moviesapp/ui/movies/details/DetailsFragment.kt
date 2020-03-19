package com.mi.moviesapp.ui.movies.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mi.moviesapp.R
import com.mi.moviesapp.ui.BaseFragment
import com.mi.moviesapp.ui.movies.MoviesViewModel
import com.mi.moviesapp.ui.movies.state.MoviesEventState
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class DetailsFragment : BaseFragment() {

    val moviesViewModel: MoviesViewModel by viewModel()
    val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.data?.let {
                it.data?.getContentIfNotHandled()?.let { viewState ->
                    viewState.movieDetailsFields.let { movies ->
                        moviesViewModel.setMoviesDetails(movies)
                    }
                }
            }
        })

        moviesViewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.movieDetailsFields?.let { movieDetailsFields ->
                tvTitle.text = movieDetailsFields.movie?.description
            }
        })

        moviesViewModel.setStateEvent(MoviesEventState.GetMovieDetailsEvent(args.id))
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_details
    }
}