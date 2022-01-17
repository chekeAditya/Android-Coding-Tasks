package com.aditya.calender.remote.response

import com.google.gson.annotations.SerializedName


data class CreateTaskClass(
    @SerializedName("user_id")
    val user_id : Int,
    @SerializedName("task")
    val task : TaskDetail
)
