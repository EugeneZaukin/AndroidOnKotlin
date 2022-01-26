package com.eugene.androidonkotlin.model

import com.google.gson.annotations.SerializedName

data class MovieDTO(
        val title: String,
        val poster: String,
        val description: String,

        @SerializedName("rating_kinopoisk")
        val rating: String
)