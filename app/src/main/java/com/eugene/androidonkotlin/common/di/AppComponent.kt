package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.MultiViewModelFactory
import dagger.Component

@Component(modules = [RepositoryModule::class,
                      NetworkModule::class,
                      MultiViewModelModule::class])
interface AppComponent {
    fun viewModelFactory(): MultiViewModelFactory
}