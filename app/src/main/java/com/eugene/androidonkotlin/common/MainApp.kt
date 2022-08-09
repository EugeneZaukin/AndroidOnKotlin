package com.eugene.androidonkotlin.common

import android.app.Application
import android.content.Context
import com.eugene.androidonkotlin.common.di.*

class MainApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }