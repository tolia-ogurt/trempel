package com.example.trempel

import android.app.Application
import com.example.trempel.di.AppComponent
import com.example.trempel.di.DaggerAppComponent

internal class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}