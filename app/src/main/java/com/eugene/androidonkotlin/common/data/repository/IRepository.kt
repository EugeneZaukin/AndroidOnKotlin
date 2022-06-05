package com.eugene.androidonkotlin.common.data.repository

import com.eugene.androidonkotlin.common.data.model.*

interface IRepository {
    suspend fun getMoviesFromServer(): JsonWelcome

    suspend fun getMovieFromServer(movieId: Long): DescriptionMovie

    fun getMovieFromLocalServer(): List<MainMovie>
}