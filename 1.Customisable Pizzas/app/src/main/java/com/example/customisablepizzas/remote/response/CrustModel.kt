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
/*
https://api.themoviedb.org/3/discover/movie?api_key=328c283cd27bd1877d9080ccb1604c91&primary_release_date.lte=2016-12-31&sort_by=release_date.desc&page=1

 */