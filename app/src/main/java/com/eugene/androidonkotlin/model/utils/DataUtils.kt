package com.eugene.androidonkotlin.model.utils

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO

fun convertDtoToModel(movieDTO: MovieDTO) : List<Movie> {
    return listOf(Movie(movieDTO.title!!, movieDTO.poster!!, movieDTO.rating_kinopoisk!!, movieDTO.description!!))
}