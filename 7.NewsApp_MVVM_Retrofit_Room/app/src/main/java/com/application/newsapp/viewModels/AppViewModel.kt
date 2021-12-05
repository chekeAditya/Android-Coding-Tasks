package com.application.newsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.newsapp.remote.Resource
import com.application.newsapp.remote.responses.ArticleModel
import com.application.newsapp.remote.responses.ResponseModel
import com.application.newsapp.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(var appRepository: AppRepository) : ViewModel() {


    fun getDataFromAPI(): LiveData<Resource<ResponseModel>> {
        return liveData(Dispatchers.IO) {
            val data = appRepository.getDataFromApi()
            emit(data)
        }
    }

    fun insertDataToDb(articleModel: List<ArticleModel>) {
        appRepository.insertDatatInDb(articleModel)
    }

    fun getDataFromDb(): LiveData<List<ArticleModel>> {
        return appRepository.getDataFromDb()
    }

    fun deleteDataFromDb() {
        appRepository.deleteFromDb()
    }

}