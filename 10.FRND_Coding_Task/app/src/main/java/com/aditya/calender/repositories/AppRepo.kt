package com.aditya.calender.repositories

import com.aditya.calender.remote.interfaces.APIClient
import com.aditya.calender.remote.Resource
import com.aditya.calender.remote.ResponseHandler
import com.aditya.calender.remote.response.CreateTaskClass
import com.aditya.calender.remote.response.GetResponseDto
import com.aditya.calender.remote.response.UserIdResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepo @Inject constructor(val apiClient: APIClient) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getResponseda(): Resource<GetResponseDto> {
        return try {

            val response = apiClient.getTask(UserIdResponseModel(1001))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }


    fun createTask(createTaskClass: CreateTaskClass) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiClient.createTask(
                token = "d95a5f11-13ef-419a-be7e-5a64cac73624",
                createTaskClass
            )
        }
    }

    fun deleteTask(delete: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            apiClient.deleteTask(1001, delete)
        }
    }
}