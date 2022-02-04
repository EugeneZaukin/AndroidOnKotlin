package com.eugene.androidonkotlin.repository.local

import com.eugene.androidonkotlin.model.MainMovie

interface LocalRepository {
    fun getAllHistory():List<MainMovie>
    fun saveEntity(mainMovie: MainMovie)
}