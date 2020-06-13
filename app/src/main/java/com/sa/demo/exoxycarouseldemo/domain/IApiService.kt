package com.sa.demo.exoxycarouseldemo.domain

import com.sa.demo.exoxycarouseldemo.data.MovieData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
interface IApiService {

    @GET("top_rated?api_key=762d3d555ea8de3401f5f7d7e1b61834&language=en-US")
    fun getTopRatedMovies(@Query("page") page: Int = 1):
            Deferred<Response<MovieData>>
}