package com.eugene.androidonkotlin.common.di

import com.eugene.androidonkotlin.common.data.repository.MovieAPi
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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