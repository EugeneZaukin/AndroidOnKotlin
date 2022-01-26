package com.eugene.androidonkotlin.model

import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getMoviesFromServer(): Single<MovieDTO>

    fun getMovieFromLocalServer(): List<Movie>
}