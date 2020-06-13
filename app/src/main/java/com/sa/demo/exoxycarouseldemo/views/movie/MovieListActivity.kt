package com.sa.demo.exoxycarouseldemo.views.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 4/6/20
 */
class MovieListActivity : AppCompatActivity() {

    private val mViewModel by viewModel<MovieListViewModel>()
    private val moviePagedController by lazy { MovieController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        rv_movie.adapter = moviePagedController.adapter
        mViewModel.getMovies().observe(this, Observer {
            moviePagedController.submitList(it)
        })

        /*mViewModel.getHorizontalMovies().observe(this, Observer {
            moviePagedController.submitHorizontalList(it)
        })*/

        mViewModel.getNetworksStateLiveData().observe(this, Observer {
            it?.let {
                when (it) {
                    is Resource.Loading -> {
                        moviePagedController.isLoading = true
                    }
                    is Resource.Success -> {
                        moviePagedController.isLoading = false
                    }
                }

            }
        })
    }
}