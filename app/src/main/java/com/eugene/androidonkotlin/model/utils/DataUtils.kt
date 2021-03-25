package com.eugene.androidonkotlin.model.utils

import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO
import com.eugene.androidonkotlin.room.HistoryEntity

fun convertDtoToModel(movieDTO: MovieDTO) : List<Movie> {
    return listOf(Movie(movieDTO.title!!, movieDTO.poster!!, movieDTO.rating_kinopoisk!!, movieDTO.description!!))
}

fun convertHistoryEntityToMovie(entityList: List<HistoryEntity>): List<Movie> {
    return entityList.map {
        Movie(it.title,"", it.rating, "");
    }
}

fun convertMovieToEntity(movie: Movie): HistoryEntity {
    return HistoryEntity(0, movie.title, movie.rating)
}