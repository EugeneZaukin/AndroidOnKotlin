package com.eugene.androidonkotlin.model.description_repository

import com.eugene.androidonkotlin.model.MovieDTO
import retrofit2.Callback

class DecriptionRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DescriptionRepository {
    override fun getMovieDescriptionFromServer(callback: Callback<MovieDTO>) {
//        remoteDataSource.getMovieDescription(callback)
    }

}