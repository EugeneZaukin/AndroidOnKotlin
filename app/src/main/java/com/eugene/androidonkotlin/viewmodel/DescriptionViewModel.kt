package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.CodeErrors
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.repository.local.LocalRepository
import com.eugene.androidonkotlin.repository.local.LocalRepositoryImpl
import com.eugene.androidonkotlin.repository.remote.IRepository
import com.eugene.androidonkotlin.repository.remote.IRepositoryImpl
import com.eugene.androidonkotlin.viewmodel.App.Companion.getHistoryDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DescriptionViewModel(
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao()),
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

    private val _error =
        MutableSharedFlow<CodeErrors>(0, 1, BufferOverflow.DROP_OLDEST)
    val error = _error.asSharedFlow()

    private val _switchDescriptionFragment: MutableSharedFlow<Int> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)
    val switchDescriptionFragment get() = _switchDescriptionFragment.asSharedFlow()

    private var id: Long = 0;

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
                { println(it) }
            )


    }

    fun saveMovieToDB(movie: Movie) {
//        historyRepository.saveEntity(Movie(movie.title, "", movie.rating, ""))
    }
}