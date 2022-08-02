package com.example.softcorptest

import android.app.Application
import android.content.Context
import com.example.softcorptest.presentation.di.AppComponent
import com.example.softcorptest.presentation.di.DaggerAppComponent

class SoftcorpApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is SoftcorpApp -> appComponent
        else -> applicationContext.appComponent
    }