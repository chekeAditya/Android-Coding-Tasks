package com.example.customisablepizzas.remote.response


import com.google.gson.annotations.SerializedName

data class MainPizzaResponse(
    @SerializedName("crusts")
    val crusts: List<Crust>,
    @SerializedName("defaultCrust")
    val defaultCrust: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("isVeg")
    val isVeg: Boolean,
    @SerializedName("name")
    val name: String
) {
}