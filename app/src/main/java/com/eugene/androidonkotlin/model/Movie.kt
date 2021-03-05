package com.eugene.androidonkotlin.model

import com.eugene.androidonkotlin.R

data class Movie(
        val title: String = "Властелин колец: Братство кольца",
        val image: Int = R.drawable.lordofthering,
        val rating: String = "Кинопоиск 8.6"
)
