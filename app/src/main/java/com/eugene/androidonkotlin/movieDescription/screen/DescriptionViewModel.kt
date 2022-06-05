package com.eugene.androidonkotlin.movieDescription.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugene.androidonkotlin.common.screen.CodeErrors
import com.eugene.androidonkotlin.common.data.model.MainMovie
import com.eugene.androidonkotlin.common.data.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
//    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao()),
    private val remoteRepo: IRepository
): ViewModel() {
    private val _loadingProgress = MutableStateFlow(1f)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _movieTitle = MutableStateFlow("")
    val movieTitle get() = _movieTitle.asStateFlow()

    private val _movieImage = MutableStateFlow("")
    val movieImage get() = _movieImage.asStateFlow()

    private val _movieOverview = MutableStateFlow("")
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

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movieFromServer = remoteRepo.getMovieFromServer(id)
                _movieTitle.tryEmit(movieFromServer.title)
                _movieImage.tryEmit(movieFromServer.backdropPath)
                _movieOverview.tryEmit(movieFromServer.overview)
                _loadingProgress.value = 0f
            } catch (exp: Exception) {
                _loadingProgress.value = 0f
                when {
                    exp.toString().contains("404") ->
                        _errorCode.tryEmit(CodeErrors.REQUEST_ERROR)
                    exp.toString().contains("500") ->
                        _errorCode.tryEmit(CodeErrors.SERVER_ERROR)
                    else ->
                        _errorCode.tryEmit(CodeErrors.NETWORK_ERROR)
                }
            }
        }
    }

    fun saveMovieToDB(mainMovie: MainMovie) {
//        historyRepository.saveEntity(Movie(movie.title, "", movie.rating, ""))
    }
}