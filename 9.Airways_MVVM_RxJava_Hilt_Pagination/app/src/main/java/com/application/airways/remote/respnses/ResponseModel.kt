package com.application.airways.remote.respnses


import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("data")
    val `data`: List<DataModel>,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalPassengers")
    val totalPassengers: Int
)