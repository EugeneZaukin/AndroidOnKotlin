package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.MovieDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieAPi {
    @GET("movies/1143242/token/fe198beca21b5f01d844f2db52d2bb2f")
    fun getMovie(): Single<List<MovieDTO>>
}