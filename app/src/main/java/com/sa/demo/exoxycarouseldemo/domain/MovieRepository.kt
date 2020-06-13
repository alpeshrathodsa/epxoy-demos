package com.sa.demo.exoxycarouseldemo.domain

import androidx.lifecycle.MutableLiveData
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.data.MovieData

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
interface MovieRepository {

    val networkStatusLiveData: MutableLiveData<Resource<MovieData>>
    suspend fun getMovies(page: Int): Any?
}