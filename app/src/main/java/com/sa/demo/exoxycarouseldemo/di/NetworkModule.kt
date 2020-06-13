package com.sa.demo.exoxycarouseldemo.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sa.demo.exoxycarouseldemo.base.utils.ApiConstant
import com.sa.demo.exoxycarouseldemo.domain.IApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/3/20
 */
val networkModule = module {
    // Dependency: Retrofit
    single {
        Retrofit.Builder().baseUrl(ApiConstant.BASE_URL).client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    // Dependency: ApiService
    single {
        val retrofit: Retrofit = get()
        retrofit.create(IApiService::class.java)
    }
}