package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.data.repository.IRepository
import com.eugene.androidonkotlin.common.data.repository.IRepositoryImpl
import com.eugene.androidonkotlin.common.data.repository.MovieAPi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun remoteRepository(movieApi: MovieAPi): IRepository = IRepositoryImpl(movieApi)
}