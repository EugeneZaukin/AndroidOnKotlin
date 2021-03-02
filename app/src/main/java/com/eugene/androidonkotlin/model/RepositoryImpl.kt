package com.eugene.androidonkotlin.model

class RepositoryImpl: Repository {

    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalServer(): Movie {
        return Movie()
    }
}