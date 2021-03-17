package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieAPI {

    @GET("movies/1143242/token/fe198beca21b5f01d844f2db52d2bb2f")
    fun getMovie(
        @Header("api.kinopoisk.cloud") token: String,
    ): Call<MovieDTO>
}