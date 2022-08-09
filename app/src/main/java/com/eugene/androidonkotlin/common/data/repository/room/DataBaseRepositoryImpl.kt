package com.eugene.androidonkotlin.common.data.repository.room

class DataBaseRepositoryImpl(private val dataBase: AppDataBase) : IDataBaseRepository {
    override fun insertMovie(movie: MovieDB) {
        dataBase.movieDao().insertMovie(movie)
    }

    override fun deleteMovie(movie: MovieDB) {
        dataBase.movieDao().deleteMovie(movie)
    }

    override fun getMovies(): List<MovieDB> = dataBase.movieDao().getMovies()

    override fun getMovieByRemoteId(id: Long): MovieDB = dataBase.movieDao().getMovieByRemoteId(id)
}