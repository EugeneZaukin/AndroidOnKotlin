package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.repository.remote.IRepository
import com.eugene.androidonkotlin.repository.remote.IRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val repoImp: IRepository = IRepositoryImpl()) : ViewModel() {
    private val _loadingProgress = MutableStateFlow<Float>(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _moviesList = MutableStateFlow<List<Movie>>(listOf())
    val moviesList get() = _moviesList.asStateFlow()

    fun getMoviesFromServer() {
        repoImp.getMoviesFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _moviesList.value = it.results },
                { print("error") }
            )
    }
}