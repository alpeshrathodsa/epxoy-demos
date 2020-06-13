package com.sa.demo.exoxycarouseldemo

import androidx.multidex.MultiDex
import com.sa.demo.exoxycarouseldemo.base.base.BaseApp
import com.sa.demo.exoxycarouseldemo.base.di.baseAppModule
import com.sa.demo.exoxycarouseldemo.base.di.baseNetworkModule
import com.sa.demo.exoxycarouseldemo.di.movieModules
import com.sa.demo.exoxycarouseldemo.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 1/6/20
 */
class DemoApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@DemoApp)
            modules(
                listOf(
                    baseAppModule,
                    baseNetworkModule,
                    networkModule,
                    movieModules
                )
            )
        }
    }
}