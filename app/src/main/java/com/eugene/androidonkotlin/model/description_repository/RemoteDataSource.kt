package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.MovieDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REQUEST_API_KEY = "api.kinopoisk.cloud"
private const val MOVIE_KEY_API = "fe198beca21b5f01d844f2db52d2bb2f"

class RemoteDataSource {
    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.cloud/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MovieAPI::class.java)

    fun getMovieDescription(callback: Callback<MovieDTO>) {
        movieApi.getMovie(MOVIE_KEY_API).enqueue(callback)
    }

//    fun getMovieDescription(requestLink: String, callback: okhttp3.Callback) {
//        val builder = Request.Builder().apply { header(REQUEST_API_KEY, MOVIE_KEY_API).url(requestLink)}
//        OkHttpClient().newCall(builder.build()).enqueue(callback)
//    }
}