package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.JsonWelcome
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO
import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getMoviesFromServer(): Single<JsonWelcome>

    fun getMovieFromLocalServer(): List<Movie>
}