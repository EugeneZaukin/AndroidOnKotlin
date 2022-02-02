package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.CodeErrors
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.repository.remote.IRepository
import com.eugene.androidonkotlin.repository.remote.IRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel(private val repoImp: IRepository = IRepositoryImpl()) : ViewModel() {
    private val _loadingProgress = MutableStateFlow<Float>(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _moviesList = MutableStateFlow<List<Movie>>(listOf())
    val moviesList get() = _moviesList.asStateFlow()

    private val _errorCode =
        MutableSharedFlow<CodeErrors>(0,1, BufferOverflow.DROP_OLDEST)
    val errorCode = _errorCode.asSharedFlow()

    private val _switchDescriptionFragment: MutableSharedFlow<Long> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)
    val switchDescriptionFragment get() = _switchDescriptionFragment.asSharedFlow()

    fun getMoviesFromServer() {
        _loadingProgress.value = 1f

        repoImp.getMoviesFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _moviesList.value = it.results
                    _loadingProgress.value = 0f
                },
                {
                    _loadingProgress.value = 0f
                    when {
                        it.toString().contains("404") -> _errorCode.tryEmit(CodeErrors.REQUEST_ERROR)
                        it.toString().contains("500") -> _errorCode.tryEmit(CodeErrors.SERVER_ERROR)
                        else -> _errorCode.tryEmit(CodeErrors.NETWORK_ERROR)
                    }
                }
            )
    }

    fun goToDescriptionScreen(pos: Int) {
        _switchDescriptionFragment.tryEmit(_moviesList.value[pos].id)
    }
}