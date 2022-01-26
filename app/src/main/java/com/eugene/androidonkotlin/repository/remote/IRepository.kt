package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO
import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getMoviesFromServer(): Single<List<MovieDTO>>

    fun getMovieFromLocalServer(): List<Movie>
}