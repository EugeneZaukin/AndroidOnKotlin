package com.eugene.androidonkotlin.model

class RepositoryImpl: Repository {
    override fun getMovieFromServer() = Movie()
    override fun getMovieFromLocalServer() = getListMovies()
}