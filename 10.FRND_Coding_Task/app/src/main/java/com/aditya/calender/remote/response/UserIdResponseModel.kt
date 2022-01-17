package com.aditya.calender.remote.response


import com.google.gson.annotations.SerializedName

data class UserIdResponseModel(
    @SerializedName("user_id")
    val userId: Int
)