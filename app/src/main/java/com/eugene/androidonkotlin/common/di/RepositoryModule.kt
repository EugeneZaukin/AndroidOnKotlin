package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.data.repository.*
import com.eugene.androidonkotlin.common.data.repository.room.*
import dagger.*

@Module
class RepositoryModule {
    @Provides
    fun remoteRepository(movieApi: MovieAPi, dataBase: AppDataBase): IRepository =
        IRepositoryImpl(movieApi, dataBase)

    @Provides
    fun provideDataBaseRepository(dataBase: AppDataBase): IDataBaseRepository =
        DataBaseRepositoryImpl(dataBase)
}