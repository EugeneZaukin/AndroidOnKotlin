package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.repository.remote.IRepository
import com.eugene.androidonkotlin.repository.remote.IRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val repoImp: IRepository = IRepositoryImpl()) : ViewModel() {
    private val _loadingProgress = MutableStateFlow<Float>(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _image = MutableStateFlow<String>("")
    val image get() = _image.asStateFlow()

    private val _title = MutableStateFlow<String>("")
    val title get() = _title.asStateFlow()

    private val _rating = MutableStateFlow<String>("")
    val rating get() = _rating.asStateFlow()


    fun getMoviesFromServer() {
        repoImp.getMoviesFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println(it . results [0].title)
                    println(it . results [1].title)
                    println(it . results [2].title)
                    println(it . results [3].title)
                },


                { print("lol") }
            )
    }
}