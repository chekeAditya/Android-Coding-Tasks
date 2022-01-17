package com.application.airways.remote.respnses


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("airline")
    val airlineModel: List<AirlineModel>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("trips")
    val trips: Int,
    @SerializedName("__v")
    val v: Int
)