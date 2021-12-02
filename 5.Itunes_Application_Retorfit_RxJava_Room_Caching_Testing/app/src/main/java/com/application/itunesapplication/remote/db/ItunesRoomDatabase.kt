package com.application.itunesapplication.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.itunesapplication.remote.responses.ResultModel

@Database(entities = [ResultModel::class], version = 1)
abstract class ItunesRoomDatabase : RoomDatabase() {

    abstract fun getResponseFromDao(): AppDao

}