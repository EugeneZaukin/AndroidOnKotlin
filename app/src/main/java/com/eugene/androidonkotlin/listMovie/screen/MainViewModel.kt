package com.eugene.androidonkotlin.listMovie.screen

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.common.screen.CodeErrors
import com.eugene.androidonkotlin.common.data.model.MainMovie
import com.eugene.androidonkotlin.common.data.repository.IRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val remoteRepo: IRepository
): ViewModel() {
    private val _loadingProgress = MutableStateFlow<Boolean>(false)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _moviesList = MutableStateFlow<List<MainMovie>>(listOf())
    val moviesList get() = _moviesList.asStateFlow()

    private val _errorCode =
        MutableSharedFlow<CodeErrors>(0,1, BufferOverflow.DROP_OLDEST)
    val errorCode = _errorCode.asSharedFlow()

    private val _switchDescriptionFragment: MutableSharedFlow<Long> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)
    val switchDescriptionFragment get() = _switchDescriptionFragment.asSharedFlow()

    fun getMoviesFromServer() {
        _loadingProgress.value = true

        remoteRepo.getMoviesFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _moviesList.value = it.results
                    _loadingProgress.value = false
                },
                {
                    _loadingProgress.value = false
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