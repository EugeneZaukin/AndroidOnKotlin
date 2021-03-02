package com.eugene.androidonkotlin.model

import com.eugene.androidonkotlin.R

data class Movie(
        val title: String = "Фильм",
        val image: Int = R.drawable.lordofthering,
        val rating: String = "0"
)
