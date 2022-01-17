package com.aditya.calender.remote.interfaces

import com.aditya.calender.remote.response.CreateTaskClass
import com.aditya.calender.remote.response.GetResponseDto
import com.aditya.calender.remote.response.TaskDetail
import com.aditya.calender.remote.response.UserIdResponseModel
import retrofit2.http.*

interface APIClient {


    @POST("api/getCalendarTaskLists")
    suspend fun getTask(
        @Body userIdResponseModel: UserIdResponseModel,
    ): GetResponseDto

    @POST("api/storeCalendarTask")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Body createTaskClass: CreateTaskClass,
    ): TaskDetail


    @Multipart
    @POST("api/deleteCalendarTask")
    suspend fun deleteTask(
        @Part("user_id") user_id: Int,
        @Part("task_id") task_id: Int,
    ) : GetResponseDto
}