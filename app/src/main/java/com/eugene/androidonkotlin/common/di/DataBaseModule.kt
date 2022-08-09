package com.eugene.androidonkotlin.common.di

import android.content.Context
import androidx.room.Room
import com.eugene.androidonkotlin.common.data.databaseRepo.AppDataBase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {
    @Provides
    fun provideAppDataBase(context: Context): AppDataBase = Room.databaseBuilder(
        context.applicationContext,
        AppDataBase::class.java,
        AppDataBase.NAME
    ).build()
}