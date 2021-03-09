package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.model.Repository
import com.eugene.androidonkotlin.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
        private val observeForLiveData: MutableLiveData<AppState> = MutableLiveData(),
        private val repoImp: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        return observeForLiveData
    }

    fun getMovieFromLocal() {
        getDataFromLocalSource()
    }

    fun getMovieFromServer() {
        getDataFromLocalSource()
    }

    private fun getDataFromLocalSource() {
        observeForLiveData.value = AppState.Loading
        Thread {
            sleep(3000)
            observeForLiveData.postValue(AppState.Success(repoImp.getMovieFromLocalServer()))
        }.start()
    }
}