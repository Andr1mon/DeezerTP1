package com.example.deezertp1

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        lateinit var applicationContext: Context private set
    }

    override fun onCreate() {
        super.onCreate()
        App.applicationContext = applicationContext
    }
}