package com.eugene.androidonkotlin.common.data.databaseRepo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDB::class], version = AppDataBase.VERSION)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val NAME = "movieDB"
        const val VERSION = 1
    }
}