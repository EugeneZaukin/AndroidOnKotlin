package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.model.JsonWelcome
import com.eugene.androidonkotlin.model.MovieDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieAPi {
    @GET("4/list/1?page=1&api_key=02c5cb1d2287733c0c4e83e609c37856")
    fun getMovies(): Single<JsonWelcome>
}