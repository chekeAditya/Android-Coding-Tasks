package com.application.itunesapplication.repositories

import com.application.itunesapplication.di.Module
import com.application.itunesapplication.remote.Resource
import com.application.itunesapplication.remote.ResponseHandler
import com.application.itunesapplication.remote.db.AppDao
import com.application.itunesapplication.remote.interfaces.APIClient
import com.application.itunesapplication.remote.responses.ItunesResponseModel
import javax.inject.Inject

class AppRepository @Inject constructor(private val appDao: AppDao, val apiClient: APIClient) {

    val responseHandler: ResponseHandler = ResponseHandler()

//    fun getDataFromApi() {
//        CoroutineScope(Dispatchers.IO).launch {
//            saveToDb(apiClient.getResponse())
//        }
//    }
//
//    fun saveToDb(itunesResponseModel: ItunesResponseModel) {
//        val listOfData = ArrayList<ResultModel>()
//        listOfData.addAll(itunesResponseModel.resultModels)
//        appDao.addDataFromAPI(listOfData)
//    }
//
//    fun getAllDataFromDB(): LiveData<List<ResultModel>> {
//        return appDao.getResponseFromApi()
//    }

    suspend fun getDataFromAPI(): Resource<ItunesResponseModel> {
        return try {
            val itunesResponseModel: ItunesResponseModel = Module.getAPIService().getResponse()
            responseHandler.handleSuccess(itunesResponseModel)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}