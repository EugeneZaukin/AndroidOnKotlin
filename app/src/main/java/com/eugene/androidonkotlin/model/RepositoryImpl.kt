package com.eugene.androidonkotlin.model

import retrofit2.Retrofit

class RepositoryImpl: Repository {
    override fun getMoviesFromServer() {

    }


    override fun getMovieFromLocalServer() = getListMovies()
}