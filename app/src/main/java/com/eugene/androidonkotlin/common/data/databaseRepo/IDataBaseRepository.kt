package com.eugene.androidonkotlin.common.data.databaseRepo

interface IDataBaseRepository {
    fun insertMovie(movie: MovieDB)

    fun deleteMovie(movie: MovieDB)

    fun getMovies(): List<MovieDB>

    fun getMovieByRemoteId(id: Long): MovieDB
}