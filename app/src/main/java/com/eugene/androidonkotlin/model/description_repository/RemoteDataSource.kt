package com.eugene.androidonkotlin.model.description_repository

import okhttp3.OkHttpClient
import okhttp3.Request

private const val REQUEST_API_KEY = "api.kinopoisk.cloud"
private const val MOVIE_KEY_API = "fe198beca21b5f01d844f2db52d2bb2f"

class RemoteDataSource {

    fun getMovieDescription(requestLink: String, callback: okhttp3.Callback) {
        val builder = Request.Builder().apply { header(REQUEST_API_KEY, MOVIE_KEY_API).url(requestLink)}
        OkHttpClient().newCall(builder.build()).enqueue(callback)
    }
}