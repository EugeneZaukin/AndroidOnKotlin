package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.MovieDTO

interface DescriptionRepository {
    fun getMovieDescriptionFromServer(callback: retrofit2.Callback<MovieDTO>)
}