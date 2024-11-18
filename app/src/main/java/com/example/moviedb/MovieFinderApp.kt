package com.example.moviedb

import android.app.Application
import android.content.Context
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        instance = this
        initDebugTools()
    }
    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appContext: Context
        var instance: MovieFinderApp? = null
            private set
    }
}
//TODO Добавить WorkManager для очистки кэша
// Добавить отношения в таблицах