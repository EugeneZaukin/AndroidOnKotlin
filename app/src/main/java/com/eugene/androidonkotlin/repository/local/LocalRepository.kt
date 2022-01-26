package com.eugene.androidonkotlin.repository.local

import com.eugene.androidonkotlin.model.Movie

interface LocalRepository {
    fun getAllHistory():List<Movie>
    fun saveEntity(movie: Movie)
}