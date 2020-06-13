package com.sa.demo.exoxycarouseldemo.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sa.demo.exoxycarouseldemo.base.model.EnumLoading
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.data.Movie
import com.sa.demo.exoxycarouseldemo.data.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
class MovieKeyedDataSource constructor(private val movieRepository: MovieRepository, private val coroutineScope: CoroutineScope) : PageKeyedDataSource<Int, Movie>() {


    val networkStatusLiveData: MutableLiveData<Resource<MovieData>> = movieRepository.networkStatusLiveData

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        coroutineScope.launch {
            networkStatusLiveData.postValue(Resource.Loading(EnumLoading.LOADING_FIRST_PAGE))
            val result = movieRepository.getMovies(page = 1)
            if (result is MovieData) {
                result.movies?.let {
                    callback.onResult(it, 0, result.totalResults ?: 0, null, 2)
                    networkStatusLiveData.postValue(Resource.Success(result))
                }
            }
        }


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            networkStatusLiveData.postValue(Resource.Loading(EnumLoading.LOADING_MORE))
            val result = movieRepository.getMovies(params.key)
            if (result is MovieData) {
                result.movies?.let {
                    callback.onResult(it, params.key?.plus(1))
                    networkStatusLiveData.postValue(Resource.Success(result))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    override fun invalidate() {
        super.invalidate()
        coroutineScope.cancel()
    }
}