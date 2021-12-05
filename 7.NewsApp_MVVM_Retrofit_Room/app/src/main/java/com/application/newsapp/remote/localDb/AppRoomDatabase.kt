package com.application.newsapp.remote.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.newsapp.remote.responses.ArticleModel

@Database(entities = [ArticleModel::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getResponseFromDao(): AppDao

}