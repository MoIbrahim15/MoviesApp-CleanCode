package com.mi.moviesapp.ui.movies.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mi.moviesapp.R
import com.mi.moviesapp.ui.BaseFragment
import com.mi.moviesapp.ui.movies.MoviesViewModel
import com.mi.moviesapp.ui.movies.state.MoviesEventState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_sheet_movie.*
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
                movieDetailsFields.movie?.let { movie ->
                    val p: List<String> = movie.image.split("/")
                    val imageLink = "https://drive.google.com/uc?export=download&id=" + p[5]
                    Picasso.get().load(imageLink).placeholder(R.drawable.ic_launcher_background).into(imgMovie)


                    val bottomSheetBehavior: BottomSheetBehavior<*> =
                        BottomSheetBehavior.from<View>(bottom_sheet)

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    tvTitle.text = movie.name
                    tvDesc.text = movie.description
                }
            }
        })

        moviesViewModel.setStateEvent(MoviesEventState.GetMovieDetailsEvent(args.id))
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_details
    }
}