package com.eugene.androidonkotlin.model

interface Repository {
    fun getMovieFromServer(): Movie
    fun getMovieFromLocalServer(): List<Movie>
}