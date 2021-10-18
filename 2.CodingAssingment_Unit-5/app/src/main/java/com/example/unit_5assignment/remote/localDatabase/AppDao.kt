package com.example.unit_5assignment.remote.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unit_5assignment.remote.responses.TVMazeResponseModelItem

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setDataInRoom(responseModelItem: TVMazeResponseModelItem)

    @Query("select * from TVMazeResponseModel")
    fun getDataFromRoom():LiveData<List<TVMazeResponseModelItem>>

}