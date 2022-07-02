package com.eugene.androidonkotlin.common.extensions

import com.eugene.androidonkotlin.common.data.model.DescriptionMovie
import com.eugene.androidonkotlin.common.data.model.MainMovie
import com.eugene.androidonkotlin.listMovie.screen.model.Movie
import com.eugene.androidonkotlin.movieDescription.MovieDetails

fun MainMovie.toMovie() =
    Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIDS = genreIDS,
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

fun DescriptionMovie.toDetailsMovie() =
    MovieDetails(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        homepage = homepage,
        id = id,
        imdbID = imdbID,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )