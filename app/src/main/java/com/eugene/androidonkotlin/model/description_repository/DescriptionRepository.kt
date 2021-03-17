package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.MovieDTO
import okhttp3.Callback

interface DescriptionRepository {
    //fun getMovieDescriptionFromServer(requesLink: String, callback: Callback)

    fun getMovieDescriptionFromServer(callback: retrofit2.Callback<MovieDTO>)
}