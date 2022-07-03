package com.eugene.androidonkotlin.common.data.repository

import androidx.paging.PagingData
import com.eugene.androidonkotlin.common.data.model.*
import com.eugene.androidonkotlin.listMovie.screen.model.Movie
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getMoviesFromServer(pageId: Int): JsonWelcome

    suspend fun getMovieFromServer(movieId: Long): DescriptionMovie

    fun getPagedMovies(errorListener:(Exception) -> Unit): Flow<PagingData<Movie>>

    fun getMovieFromLocalServer(): List<MainMovie>
}