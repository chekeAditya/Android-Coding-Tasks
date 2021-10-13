package com.example.customisablepizzas.remote.response


import com.google.gson.annotations.SerializedName

data class CrustModel(
    @SerializedName("defaultSize")
    val defaultSize: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sizes")
    val sizeModels: List<SizeModel>
)