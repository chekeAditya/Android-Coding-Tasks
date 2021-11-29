package com.application.addresssearch.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("data")
    val dataModel: DataModel,
    @SerializedName("requestId")
    val requestId: String
)