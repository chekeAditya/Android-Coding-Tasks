package com.example.unit_5assignment.remote.responses


import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: String
)