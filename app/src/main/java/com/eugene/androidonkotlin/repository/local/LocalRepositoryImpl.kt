package com.eugene.androidonkotlin.repository.local

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.room.HistoryDao

class LocalRepositoryImpl(private val localDataSource: HistoryDao): LocalRepository {

    override fun getAllHistory(): List<Movie> {
        return listOf()
    }

    override fun saveEntity(movie: Movie) {
//        localDataSource.insert(convertMovieToEntity(movie))
    }
}