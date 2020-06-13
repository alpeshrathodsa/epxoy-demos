package com.sa.demo.exoxycarouseldemo.di

import com.sa.demo.exoxycarouseldemo.domain.MovieDatasourceFactory
import com.sa.demo.exoxycarouseldemo.domain.MovieKeyedDataSource
import com.sa.demo.exoxycarouseldemo.domain.MovieRepository
import com.sa.demo.exoxycarouseldemo.domain.MovieRepositoryImpl
import com.sa.demo.exoxycarouseldemo.viewmodel.MovieListViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */

val movieModules = module {

    single { MovieRepositoryImpl(get(), get(), get()) as MovieRepository }
    single { (scope: CoroutineScope) -> MovieDatasourceFactory(scope) }
    single { (scope: CoroutineScope) -> MovieKeyedDataSource(get(), scope) }
    single { MovieListViewModel() }
}