package com.eugene.androidonkotlin.repository.local

import com.eugene.androidonkotlin.model.MainMovie

class LocalRepositoryImpl(): LocalRepository {

    override fun getAllHistory(): List<MainMovie> {
        return listOf()
    }

    override fun saveEntity(mainMovie: MainMovie) {
//        localDataSource.insert(convertMovieToEntity(movie))
    }
}