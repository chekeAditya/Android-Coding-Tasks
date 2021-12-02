package com.application.itunesapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.itunesapplication.remote.Resource
import com.application.itunesapplication.remote.db.ItunesDbTable
import com.application.itunesapplication.remote.responses.ItunesResponseModel
import com.application.itunesapplication.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.math.sqrt

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun getDataFromAPI(query: String): LiveData<Resource<ItunesResponseModel>> {
        return liveData(Dispatchers.IO) {
            val data = appRepository.getDataFromAPI(query)
            emit(data)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        appRepository.insertDataInDb(itunesDbTable)
    }

    fun deleteDataFromDb() {
        appRepository.deleteDataFromDb()
    }

    fun getDataFromDb() {
        appRepository.getDataFromDb()
    }

}