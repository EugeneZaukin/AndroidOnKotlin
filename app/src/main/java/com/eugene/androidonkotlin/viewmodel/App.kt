package com.eugene.androidonkotlin.viewmodel

import android.app.Application
import androidx.room.Room
import com.eugene.androidonkotlin.room.HistoryDao
import com.eugene.androidonkotlin.room.HistoryDataBase

class App: Application() {

    companion object {
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        private val history_dao by lazy {
            Room
                .databaseBuilder(appInstance!!.applicationContext,
                    HistoryDataBase::class.java,
                    DB_NAME
                )
                .allowMainThreadQueries()
                .build()
                .historyDao()
        }

        fun getHistoryDao(): HistoryDao = history_dao
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

}