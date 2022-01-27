package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.description_repository.DecriptionRepositoryImpl
import com.eugene.androidonkotlin.model.description_repository.DescriptionRepository
import com.eugene.androidonkotlin.model.description_repository.RemoteDataSource
import com.eugene.androidonkotlin.repository.local.LocalRepository
import com.eugene.androidonkotlin.repository.local.LocalRepositoryImpl
import com.eugene.androidonkotlin.viewmodel.App.Companion.getHistoryDao

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DescriptionViewModel(
    private val descriprionLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val descriptionRepositoryImpl: DescriptionRepository = DecriptionRepositoryImpl(RemoteDataSource()),

    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
): ViewModel() {

    fun getLiveData() = descriprionLiveData

    fun getMovieFromRemoteSource() {
        descriprionLiveData.value = AppState.Loading
        descriptionRepositoryImpl.getMovieDescriptionFromServer(callback)
    }

    fun saveMovieToDB(movie: Movie) {
//        historyRepository.saveEntity(Movie(movie.title, "", movie.rating, ""))
    }



    private val callback = object : retrofit2.Callback<Movie> {

        override fun onResponse(
            call: retrofit2.Call<Movie>,
            response: retrofit2.Response<Movie>
        ) {
            val serverResponse: Movie? = response.body()
            descriprionLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: retrofit2.Call<Movie>, t: Throwable) {
            descriprionLiveData.postValue(AppState.Error(Throwable(t.message?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: Movie): AppState {
            val movie: Movie = serverResponse
            return AppState.Error(Throwable())
        }
    }
}