package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.CodeErrors
import com.eugene.androidonkotlin.model.MainMovie
import com.eugene.androidonkotlin.repository.remote.IRepository
import com.eugene.androidonkotlin.repository.remote.IRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DescriptionViewModel(
//    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao()),
    private val repoImp: IRepository = IRepositoryImpl()
): ViewModel() {
    private val _loadingProgress = MutableStateFlow<Float>(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _movieTitle = MutableStateFlow<String>("")
    val movieTitle get() = _movieTitle.asStateFlow()

    private val _movieImage = MutableStateFlow<String>("")
    val movieImage get() = _movieImage.asStateFlow()

    private val _movieOverview = MutableStateFlow<String>("")
    val movieOverview get() = _movieOverview.asStateFlow()

    private val _errorCode =
        MutableSharedFlow<CodeErrors>(0, 1, BufferOverflow.DROP_OLDEST)
    val errorCode = _errorCode.asSharedFlow()

    private var id: Long = 0

    fun saveId(id: Long) {
        this.id = id
    }


    fun getMovieFromServer() {
        _loadingProgress.value = 1f

        repoImp.getMovieFromServer(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _loadingProgress.value = 0f
                    _movieTitle.value = it.title
                    _movieImage.value = it.backdropPath
                    _movieOverview.value = it.overview
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

    fun saveMovieToDB(mainMovie: MainMovie) {
//        historyRepository.saveEntity(Movie(movie.title, "", movie.rating, ""))
    }
}