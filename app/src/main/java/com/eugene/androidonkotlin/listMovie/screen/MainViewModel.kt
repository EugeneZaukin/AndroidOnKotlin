package com.eugene.androidonkotlin.listMovie.screen

import androidx.lifecycle.*
import androidx.paging.*
import com.eugene.androidonkotlin.common.screen.CodeErrors
import com.eugene.androidonkotlin.common.data.repository.IRepository
import com.eugene.androidonkotlin.common.extensions.toMovie
import com.eugene.androidonkotlin.listMovie.screen.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val remoteRepo: IRepository
): ViewModel() {
    private val _loadingProgress = MutableStateFlow(false)
    val loadingProgress get() = _loadingProgress.asStateFlow()

    private val _moviesList = MutableStateFlow<List<Movie>>(listOf())
    val moviesList get() = _moviesList.asStateFlow()

    private val _errorCode =
        MutableSharedFlow<CodeErrors>(0,1, BufferOverflow.DROP_OLDEST)
    val errorCode = _errorCode.asSharedFlow()

    private val _switchDescriptionFragment: MutableSharedFlow<Long> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)
    val switchDescriptionFragment get() = _switchDescriptionFragment.asSharedFlow()

    private val _moviesPagedList: Flow<PagingData<Movie>> =
        remoteRepo.getPagedMovies().cachedIn(viewModelScope)
    val moviesPagedList get() = _moviesPagedList

    fun getMoviesFromServer() {
        _loadingProgress.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _moviesList.tryEmit(remoteRepo.getMoviesFromServer(1).results.map { it.toMovie() })
                _loadingProgress.value = false
            } catch (exp: Exception) {
                _loadingProgress.value = false
                handleError(exp)
            }
        }
    }

    private fun handleError(exp: Exception) {
        when {
            exp.toString().contains("404") -> _errorCode.tryEmit(CodeErrors.REQUEST_ERROR)
            exp.toString().contains("500") -> _errorCode.tryEmit(CodeErrors.SERVER_ERROR)
            else -> _errorCode.tryEmit(CodeErrors.NETWORK_ERROR)
        }
    }

    fun goToDescriptionScreen(pos: Int) {
        _switchDescriptionFragment.tryEmit(_moviesList.value[pos].id)
    }

    fun goToDescriptionScreen(movieId: Long) {
        _switchDescriptionFragment.tryEmit(movieId)
    }
}