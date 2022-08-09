package com.eugene.androidonkotlin.common.data.remoteRepo

import androidx.paging.*
import com.eugene.androidonkotlin.common.data.remoteRepo.model.*
import com.eugene.androidonkotlin.common.data.databaseRepo.AppDataBase
import com.eugene.androidonkotlin.listMovie.screen.model.Movie
import kotlinx.coroutines.flow.Flow

class IRepositoryImpl(
    private val movieApi: MovieAPi,
    private val dataBase: AppDataBase
): IRepository {
    override suspend fun getMoviesFromServer(pageId: Int): JsonWelcome = movieApi.getMovies(pageId)

    override suspend fun getMovieFromServer(movieId: Long): DescriptionMovie =
        movieApi.getMovie(movieId)

    override fun getPagedMovies(errorListener:(Exception) -> Unit): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
            pagingSourceFactory = { MoviePagingSource(movieApi, dataBase, errorListener) }
        ).flow
    }

    override fun getMovieFromLocalServer(): List<MainMovie> {
       return listOf()
    }
}