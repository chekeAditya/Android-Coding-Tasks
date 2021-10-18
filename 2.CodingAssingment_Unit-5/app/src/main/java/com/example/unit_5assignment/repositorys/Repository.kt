package com.example.unit_5assignment.repositorys

import com.example.unit_5assignment.di.Module
import com.example.unit_5assignment.remote.localDatabase.AppDao
import com.example.unit_5assignment.remote.localDatabase.DataTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(val appDao: AppDao) {

    private val apiClient = Module.provideAPIService()

    fun addDataToDB(){
        CoroutineScope(IO).launch {
            for (i in 1..328){
                val tvMazeResponseModel = apiClient.getResponse(i)
                val tvMazeResponseModelItem = ArrayList<DataTable>()
                tvMazeResponseModel.forEach{
                    val newData = DataTable(it.country.name,it.birthday,it.deathday)
                    tvMazeResponseModelItem.add(newData)
                }
                appDao.setDataInRoom(tvMazeResponseModelItem)
            }
        }
    }

}