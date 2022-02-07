package com.eugene.androidonkotlin.remains.repo

import com.eugene.androidonkotlin.common.data.model.MainMovie

interface LocalRepository {
    fun getAllHistory():List<MainMovie>
    fun saveEntity(mainMovie: MainMovie)
}