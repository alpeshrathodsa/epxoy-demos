package com.sa.demo.exoxycarouseldemo.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.data.Movie
import com.sa.demo.exoxycarouseldemo.data.MovieData
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 3/6/20
 */
class MovieDatasourceFactory(private val coroutineScope: CoroutineScope) : DataSource.Factory<Int, Movie>(), KoinComponent {

    val movieDataSource: MovieKeyedDataSource by inject { parametersOf(coroutineScope) }
    //private val movieDataSource = MovieKeyedDataSource(movieRepository, coroutineScope)


    override fun create(): DataSource<Int, Movie> {
        return movieDataSource
    }

    fun getNetworkStatusLiveData(): MutableLiveData<Resource<MovieData>> = movieDataSource.networkStatusLiveData
}