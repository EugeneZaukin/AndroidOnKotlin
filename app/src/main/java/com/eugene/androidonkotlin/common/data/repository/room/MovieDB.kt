package com.eugene.androidonkotlin.common.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val adult: Boolean,
    val backdropPath: String,
    val idRemote: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)