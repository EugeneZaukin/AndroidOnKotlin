package com.eugene.androidonkotlin.model.description_repository

import okhttp3.Callback

interface DescriptionRepository {
    fun getMovieDescriptionFromServer(requesLink: String, callback: Callback)
}