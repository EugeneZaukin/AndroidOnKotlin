package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.*
import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getMoviesFromServer(): Single<JsonWelcome>

    fun getMovieFromServer(movieId: Long): Single<Welcome>

    fun getMovieFromLocalServer(): List<Movie>
}