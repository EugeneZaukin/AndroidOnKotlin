package com.eugene.androidonkotlin.common.data.repository

import com.eugene.androidonkotlin.common.data.model.*
import io.reactivex.rxjava3.core.Single

class IRepositoryImpl(private val movieApi: MovieAPi): IRepository {
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