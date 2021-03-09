package com.eugene.androidonkotlin.viewmodel

import com.eugene.androidonkotlin.model.Movie

sealed class AppState {
    data class Success(val movieSuccess: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}