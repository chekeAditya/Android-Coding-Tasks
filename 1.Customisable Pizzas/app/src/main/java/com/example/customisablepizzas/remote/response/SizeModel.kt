package com.example.customisablepizzas.remote.response


import com.google.gson.annotations.SerializedName

data class SizeModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)