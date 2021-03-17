package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.model.MovieDTO
import com.eugene.androidonkotlin.model.description_repository.DecriptionRepositoryImpl
import com.eugene.androidonkotlin.model.description_repository.DescriptionRepository
import com.eugene.androidonkotlin.model.description_repository.RemoteDataSource
import com.eugene.androidonkotlin.model.utils.convertDtoToModel
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DescriptionViewModel(
    private val descriprionLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val descriptionRepositoryImpl: DescriptionRepository = DecriptionRepositoryImpl(RemoteDataSource())
): ViewModel() {

    fun getLiveData() = descriprionLiveData

    fun getMovieFromRemoteSource(requestLink: String) {
        descriprionLiveData.value = AppState.Loading
        descriptionRepositoryImpl.getMovieDescriptionFromServer(requestLink, callback)
    }

    private val callback = object : Callback {

        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body()?.string()
            descriprionLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call, e: IOException) {
            descriprionLiveData.postValue(AppState.Error(Throwable(e?.message?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: String): AppState {
            val movieDTO: MovieDTO = Gson().fromJson(serverResponse, MovieDTO::class.java)
            return if (movieDTO.title == null || movieDTO.rating_kinopoisk == null || movieDTO.description == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(movieDTO))
            }
        }

    }
}