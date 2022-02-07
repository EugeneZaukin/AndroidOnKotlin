package com.eugene.androidonkotlin.remains.repo

import com.eugene.androidonkotlin.common.data.model.MainMovie

class LocalRepositoryImpl(): LocalRepository {

    override fun getAllHistory(): List<MainMovie> {
        return listOf()
    }

    override fun saveEntity(mainMovie: MainMovie) {
//        localDataSource.insert(convertMovieToEntity(movie))
    }
}