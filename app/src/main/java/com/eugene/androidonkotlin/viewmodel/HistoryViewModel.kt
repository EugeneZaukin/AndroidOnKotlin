package com.eugene.androidonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.repository.LocalRepository
import com.eugene.androidonkotlin.repository.LocalRepositoryImpl
import com.eugene.androidonkotlin.viewmodel.App.Companion.getHistoryDao

class HistoryViewModel(val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
                       private val hisoryRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
): ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(hisoryRepository.getAllHistory())
    }

}