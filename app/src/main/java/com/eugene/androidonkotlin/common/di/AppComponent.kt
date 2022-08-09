package com.eugene.androidonkotlin.common.di

import android.content.Context
import com.eugene.androidonkotlin.common.MultiViewModelFactory
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    RepositoryModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    MultiViewModelModule::class
])
interface AppComponent {
    fun viewModelFactory(): MultiViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}