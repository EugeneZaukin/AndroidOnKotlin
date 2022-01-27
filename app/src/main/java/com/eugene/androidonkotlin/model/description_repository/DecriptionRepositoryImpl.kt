package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.Movie
import retrofit2.Callback

class DecriptionRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DescriptionRepository {
    override fun getMovieDescriptionFromServer(callback: Callback<Movie>) {
//        remoteDataSource.getMovieDescription(callback)
    }

}