package com.eugene.androidonkotlin.model.description_repository

class DecriptionRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DescriptionRepository {

    override fun getMovieDescriptionFromServer(requesLink: String, callback: okhttp3.Callback) {
        remoteDataSource.getMovieDescription(requesLink, callback)
    }

}