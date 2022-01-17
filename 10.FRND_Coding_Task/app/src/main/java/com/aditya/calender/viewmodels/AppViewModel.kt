package com.aditya.calender.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aditya.calender.remote.Resource
import com.aditya.calender.remote.response.CreateTaskClass
import com.aditya.calender.remote.response.GetResponseDto
import com.aditya.calender.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class AppViewModel@Inject constructor(val repo: AppRepo) : ViewModel() {

    fun createNewTask(createTaskClass: CreateTaskClass) {
        repo.createTask(createTaskClass)
    }

    fun response(): LiveData<Resource<GetResponseDto>> {
        return liveData(Dispatchers.IO) {
            val data = repo.getResponseda()
            emit(data)
        }
    }

    fun deleteTaskss(delete: Int) {
        repo.deleteTask(delete)
    }
}