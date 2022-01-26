package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.JsonWelcome
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IRepositoryImpl: IRepository {

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MovieAPi::class.java)

    override fun getMoviesFromServer(): Single<JsonWelcome> {
        return movieApi.getMovies()
    }

    override fun getMovieFromLocalServer(): List<Movie> {
       return listOf()
    }
}