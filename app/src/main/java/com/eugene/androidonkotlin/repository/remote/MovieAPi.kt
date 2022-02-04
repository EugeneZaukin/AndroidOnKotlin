package com.eugene.androidonkotlin.repository.remote

import com.eugene.androidonkotlin.BuildConfig
import com.eugene.androidonkotlin.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPi {
    @GET("4/list/1?page=1&api_key=${BuildConfig.MY_API_KEY}&language=ru")
    fun getMovies(): Single<JsonWelcome>

    @GET("3/movie/{movie_id}?api_key=${BuildConfig.MY_API_KEY}&language=ru")
    fun getMovie(@Path("movie_id") movieId: Long): Single<DescriptionMovie>
}