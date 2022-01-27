package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.Movie

interface DescriptionRepository {
    fun getMovieDescriptionFromServer(callback: retrofit2.Callback<Movie>)
}