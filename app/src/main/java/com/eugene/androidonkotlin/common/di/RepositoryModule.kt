package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.data.databaseRepo.AppDataBase
import com.eugene.androidonkotlin.common.data.databaseRepo.DataBaseRepositoryImpl
import com.eugene.androidonkotlin.common.data.databaseRepo.IDataBaseRepository
import com.eugene.androidonkotlin.common.data.remoteRepo.IRepository
import com.eugene.androidonkotlin.common.data.remoteRepo.IRepositoryImpl
import com.eugene.androidonkotlin.common.data.remoteRepo.MovieAPi
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