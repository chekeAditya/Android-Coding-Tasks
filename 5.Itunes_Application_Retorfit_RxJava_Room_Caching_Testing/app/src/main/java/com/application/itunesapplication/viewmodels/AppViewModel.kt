package com.application.itunesapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.itunesapplication.remote.Resource
import com.application.itunesapplication.remote.responses.ItunesResponseModel
import com.application.itunesapplication.remote.responses.ResultModel
import com.application.itunesapplication.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

//    fun getDataFromDb(query: String): LiveData<List<ItunesResponseModel>> {
//        return appRepository.getAllDataFromDB(query)
//    }

//    fun getDataFromAPI() {
//        appRepository.getDataFromApi()
//    }
//
//    fun getDataFromDb(): LiveData<List<ResultModel>> {
//        return appRepository.getAllDataFromDB()
//    }

    fun getDataFromAPI():LiveData<Resource<ItunesResponseModel>>{
        return liveData(Dispatchers.IO) {
            val data = appRepository.getDataFromAPI()
            emit(data)
        }
    }

}