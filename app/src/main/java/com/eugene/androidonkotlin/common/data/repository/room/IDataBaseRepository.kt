package com.eugene.androidonkotlin.common.data.repository.room

interface IDataBaseRepository {
    fun insertMovie(movie: MovieDB)

    fun deleteMovie(movie: MovieDB)

    fun getMovies(): List<MovieDB>

    fun getMovieByRemoteId(id: Long): MovieDB
}