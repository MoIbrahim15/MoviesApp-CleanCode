package com.mi.moviesapp.ui.movies.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mi.moviesapp.R
import com.mi.moviesapp.data.model.Movie
import com.mi.moviesapp.ui.BaseFragment
import com.mi.moviesapp.ui.movies.MoviesViewModel
import com.mi.moviesapp.ui.movies.state.MoviesEventState
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MoviesFragment : BaseFragment() {

    val moviesViewModel: MoviesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.data?.let {
                it.data?.getContentIfNotHandled()?.let { viewState ->
                    viewState.moviesRecyclerView.let { movies ->
                        moviesViewModel.setMovies(movies)
                    }
                }
                dataState.loading.let { loading ->
                    if (loading.isLoading)
                        progress_bar.visibility = View.VISIBLE
                    else
                        progress_bar.visibility = View.GONE

                }
            }
        })

        moviesViewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.moviesRecyclerView?.let { moviesRecyclerView ->
                moviesRecyclerView.items?.let { items ->
                    val adapter = MoviesAdapter(items, object : OnClickListener {
                        override fun onClick(item: Movie) {
                            val action =
                                MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(
                                    item.id
                                )
                            Navigation.findNavController(view).navigate(action)
                        }
                    })
                    recyclerView.adapter = adapter
                }
            }
        })

        moviesViewModel.setStateEvent(MoviesEventState.GetMoviesEvent())
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_movies
    }
}