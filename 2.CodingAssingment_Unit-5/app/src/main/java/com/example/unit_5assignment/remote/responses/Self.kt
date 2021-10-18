package com.example.unit_5assignment.remote.responses


import com.google.gson.annotations.SerializedName

data class Self(
    @SerializedName("href")
    val href: String
)