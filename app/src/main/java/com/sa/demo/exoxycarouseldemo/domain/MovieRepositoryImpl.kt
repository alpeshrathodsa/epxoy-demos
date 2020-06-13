package com.sa.demo.exoxycarouseldemo.domain

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.sa.demo.exoxycarouseldemo.base.model.BaseRepository
import com.sa.demo.exoxycarouseldemo.base.model.Resource
import com.sa.demo.exoxycarouseldemo.base.model.local.preference.PreferenceManager
import com.sa.demo.exoxycarouseldemo.data.MovieData

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
class MovieRepositoryImpl(
    private val apiService: IApiService,
    val preferenceManager: PreferenceManager,
    resource: Resources
) : BaseRepository(resource), MovieRepository {

    override val networkStatusLiveData: MutableLiveData<Resource<MovieData>> = MutableLiveData()
    override suspend fun getMovies(page: Int): Any? {
        return safeApiCall(call = { apiService.getTopRatedMovies(page).await() }, resultLiveData = networkStatusLiveData)
    }
}