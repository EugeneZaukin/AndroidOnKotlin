package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class IRepositoryImpl: IRepository {

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build().create(MovieAPi::class.java)

    override fun getMoviesFromServer(): Single<JsonWelcome> {
        return movieApi.getMovies()
    }

    override fun getMovieFromServer(movieId: Long): Single<DescriptionMovie> {
        return movieApi.getMovie(movieId)
    }

    override fun getMovieFromLocalServer(): List<MainMovie> {
       return listOf()
    }
}