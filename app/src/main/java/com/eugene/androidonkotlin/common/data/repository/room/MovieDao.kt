package com.eugene.androidonkotlin.common.data.repository.room

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: MovieDB)

    @Delete
    fun deleteMovie(movie: MovieDB)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieDB>

    @Query("SELECT * FROM movies WHERE idRemote == :id")
    fun getMovieByRemoteId(id: Long): MovieDB
}