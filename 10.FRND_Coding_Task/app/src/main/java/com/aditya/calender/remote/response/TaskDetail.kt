package com.aditya.calender.remote.response


import com.google.gson.annotations.SerializedName

data class TaskDetail(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)