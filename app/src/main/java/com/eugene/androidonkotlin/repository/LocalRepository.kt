package com.eugene.androidonkotlin.repository

import com.eugene.androidonkotlin.model.Movie

interface LocalRepository {
    fun getAllHistory():List<Movie>
    fun saveEntity(movie: Movie)
}