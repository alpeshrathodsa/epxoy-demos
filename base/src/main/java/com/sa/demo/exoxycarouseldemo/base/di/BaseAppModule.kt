package com.sa.demo.exoxycarouseldemo.base.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.sa.demo.exoxycarouseldemo.base.model.local.preference.PreferenceManager
import com.sa.demo.exoxycarouseldemo.base.utils.Constant
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val baseAppModule = module {

    //Resource
    single<Resources> {
        androidContext().resources
    }

    /*Provides shared preference instance*/
    single<SharedPreferences> { androidContext().getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE) }

    /*Dependency: PreferenceManger*/
    single {
        PreferenceManager(get())//it will take one argument i.e SharePreference from BaseAppModule.kt
    }
}