package com.eugene.androidonkotlin.repository.local

import com.eugene.androidonkotlin.model.Movie

class LocalRepositoryImpl(): LocalRepository {

    override fun getAllHistory(): List<Movie> {
        return listOf()
    }

    override fun saveEntity(movie: Movie) {
//        localDataSource.insert(convertMovieToEntity(movie))
    }
}