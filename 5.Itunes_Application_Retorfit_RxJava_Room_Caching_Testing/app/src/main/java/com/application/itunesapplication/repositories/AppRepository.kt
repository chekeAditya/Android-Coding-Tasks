package com.application.itunesapplication.repositories

import androidx.lifecycle.LiveData
import com.application.itunesapplication.di.Module
import com.application.itunesapplication.remote.Resource
import com.application.itunesapplication.remote.ResponseHandler
import com.application.itunesapplication.remote.db.AppDao
import com.application.itunesapplication.remote.db.ItunesDbTable
import com.application.itunesapplication.remote.interfaces.APIClient
import com.application.itunesapplication.remote.responses.ItunesResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepository @Inject constructor(private val appDao: AppDao, val apiClient: APIClient) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(query: String): Resource<ItunesResponseModel> {
        return try {
            val itunesResponseModel: ItunesResponseModel =
                Module.provideApiService().getResponse(query)
            responseHandler.handleSuccess(itunesResponseModel)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        CoroutineScope(Dispatchers.IO).launch {
            appDao.deleteAllDataFromDB()
            appDao.addDataFromAPI(itunesDbTable)
        }
    }

    fun getDataFromDb(): LiveData<ItunesDbTable> {
        return appDao.getResponseFromDb()
    }

    fun deleteDataFromDb(){
        appDao.deleteAllDataFromDB()
    }
}