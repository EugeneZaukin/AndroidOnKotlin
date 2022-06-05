package com.eugene.androidonkotlin.common.data.repository

import com.eugene.androidonkotlin.BuildConfig
import com.eugene.androidonkotlin.common.data.model.*
import retrofit2.http.*

interface MovieAPi {
    @GET("4/list/1?page=1&api_key=${BuildConfig.MY_API_KEY}&language=ru")
    suspend fun getMovies(): JsonWelcome

    @GET("3/movie/{movie_id}?api_key=${BuildConfig.MY_API_KEY}&language=ru")
    suspend fun getMovie(@Path("movie_id") movieId: Long): DescriptionMovie
}