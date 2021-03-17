package com.eugene.androidonkotlin.model.utils

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO

fun convertDtoToModel(movieDTO: MovieDTO) : List<Movie> {
    return listOf(Movie(movieDTO.title!!, rating = movieDTO.rating_kinopoisk!!, description = movieDTO.description!!))
}