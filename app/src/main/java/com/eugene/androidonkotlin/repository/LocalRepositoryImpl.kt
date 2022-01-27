package com.eugene.androidonkotlin.repository

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.utils.convertHistoryEntityToMovie
import com.eugene.androidonkotlin.model.utils.convertMovieToEntity
import com.eugene.androidonkotlin.room.HistoryDao

class LocalRepositoryImpl(private val localDataSource: HistoryDao): LocalRepository {

    override fun getAllHistory(): List<Movie> {
        return convertHistoryEntityToMovie(localDataSource.all())
    }

    override fun saveEntity(movie: Movie) {
        localDataSource.insert(convertMovieToEntity(movie))
    }
}