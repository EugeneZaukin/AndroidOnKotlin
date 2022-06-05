package com.eugene.androidonkotlin.common.data.repository

import com.eugene.androidonkotlin.common.data.model.DescriptionMovie
import com.eugene.androidonkotlin.common.data.model.JsonWelcome
import com.eugene.androidonkotlin.common.data.model.MainMovie
import io.reactivex.rxjava3.core.Single

interface IRepository {
    suspend fun getMoviesFromServer(): JsonWelcome

    fun getMovieFromServer(movieId: Long): Single<DescriptionMovie>

    fun getMovieFromLocalServer(): List<MainMovie>
}