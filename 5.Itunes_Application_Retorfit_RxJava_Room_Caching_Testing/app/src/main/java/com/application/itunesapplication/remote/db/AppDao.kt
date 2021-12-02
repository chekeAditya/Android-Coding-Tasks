package com.application.itunesapplication.remote.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.itunesapplication.remote.responses.ResultModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDataFromAPI(result: List<ResultModel?>)

    @Query("select * from itune_details")
    fun getResponseFromApi() : LiveData<List<ResultModel?>>

}