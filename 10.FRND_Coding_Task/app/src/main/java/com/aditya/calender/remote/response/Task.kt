package com.aditya.calender.remote.response


import com.aditya.calender.remote.response.TaskDetail
import com.google.gson.annotations.SerializedName


data class Task(
    @SerializedName("task_detail")
    val taskDetail: TaskDetail,
    @SerializedName("task_id")
    val taskId: Int
)