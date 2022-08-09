package com.eugene.androidonkotlin.common.data.databaseRepo

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieDB)

    @Delete
    fun deleteMovie(movie: MovieDB)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieDB>

    @Query("SELECT * FROM movies WHERE idRemote == :id")
    fun getMovieByRemoteId(id: Long): MovieDB
}