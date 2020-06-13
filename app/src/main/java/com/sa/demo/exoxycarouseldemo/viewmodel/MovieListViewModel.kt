package com.sa.demo.exoxycarouseldemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.data.Movie
import com.sa.demo.exoxycarouseldemo.data.MovieData
import com.sa.demo.exoxycarouseldemo.domain.MovieDatasourceFactory
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf


private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE_HINT = 25

class MovieListViewModel : ViewModel(), KoinComponent {

    private var moviesLiveData: LiveData<PagedList<Movie>>
    //private var horizontalMoviesLiveData: LiveData<PagedList<Movie>>

    private val dataSourceFactory: MovieDatasourceFactory by inject { parametersOf(viewModelScope) }
    //private val datasourceFactory = datasourceFactory(movieRepository, viewModelScope)


    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        moviesLiveData = LivePagedListBuilder<Int, Movie>(dataSourceFactory, config).build()
        //horizontalMoviesLiveData = LivePagedListBuilder<Int, Movie>(dataSourceFactory, config).build()
    }


    fun getMovies(): LiveData<PagedList<Movie>> = moviesLiveData
    //fun getHorizontalMovies(): LiveData<PagedList<Movie>> = horizontalMoviesLiveData
    fun getNetworksStateLiveData(): LiveData<Resource<MovieData>> = dataSourceFactory.getNetworkStatusLiveData()

}

