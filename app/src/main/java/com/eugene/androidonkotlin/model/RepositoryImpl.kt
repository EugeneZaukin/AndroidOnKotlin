package com.eugene.androidonkotlin.model

class RepositoryImpl: Repository {

    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalServer(): List<Movie> {
        return getListMovies()
    }
}