package com.example.unit_5assignment.remote.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unit_5assignment.remote.responses.TVMazeResponseModelItem

@Database(entities = [TVMazeResponseModelItem::class], version = 2)
abstract class ApplicationRoomDatabase : RoomDatabase() {

    abstract fun getResponseFromDao(): AppDao

}