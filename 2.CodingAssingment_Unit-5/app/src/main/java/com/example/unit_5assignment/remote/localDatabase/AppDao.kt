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
    fun setDataInRoom(responseModelItem: ArrayList<DataTable>)

    @Query("select * from table_tv_maze")
    fun getDataFromRoom(): LiveData<List<TVMazeResponseModelItem>>

    @Query("delete from table_tv_maze")
    fun deleteAllData()

}