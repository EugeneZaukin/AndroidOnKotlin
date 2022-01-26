package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.model.Repository
import com.eugene.androidonkotlin.model.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val repoImp: Repository = RepositoryImpl()) : ViewModel() {
    private val _loadingProgress = MutableStateFlow<Float>(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _image = MutableStateFlow<String>("")
    val image get() = _image.asStateFlow()

    private val _title = MutableStateFlow<String>("")
    val title get() = _title.asStateFlow()

    private val _rating = MutableStateFlow<String>("")
    val rating get() = _rating.asStateFlow()


    fun getMoviesFromServer() {
        repoImp.getMovieFromServer()
    }
}