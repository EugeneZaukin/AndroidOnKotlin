package com.eugene.androidonkotlin.common.data.remoteRepo

import com.eugene.androidonkotlin.BuildConfig
import com.eugene.androidonkotlin.common.data.remoteRepo.model.*
import retrofit2.http.*

interface MovieAPi {
    @GET("4/list/1?api_key=${BuildConfig.MY_API_KEY}&language=ru")
    suspend fun getMovies(@Query("page") pageId: Int): JsonWelcome

    @GET("3/movie/{movie_id}?api_key=${BuildConfig.MY_API_KEY}&language=ru")
    suspend fun getMovie(@Path("movie_id") movieId: Long): DescriptionMovie
}