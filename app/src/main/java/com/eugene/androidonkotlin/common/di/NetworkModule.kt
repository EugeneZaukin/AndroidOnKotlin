package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.data.remoteRepo.MovieAPi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun api(retrofit: Retrofit): MovieAPi = retrofit.create(MovieAPi::class.java)

    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}