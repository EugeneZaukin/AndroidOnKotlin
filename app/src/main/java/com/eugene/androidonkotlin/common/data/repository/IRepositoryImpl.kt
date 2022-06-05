package com.eugene.androidonkotlin.common.data.repository

import com.eugene.androidonkotlin.common.data.model.*

class IRepositoryImpl(private val movieApi: MovieAPi): IRepository {
    override suspend fun getMoviesFromServer(): JsonWelcome = movieApi.getMovies()

    override suspend fun getMovieFromServer(movieId: Long): DescriptionMovie =
        movieApi.getMovie(movieId)

    override fun getMovieFromLocalServer(): List<MainMovie> {
       return listOf()
    }
}