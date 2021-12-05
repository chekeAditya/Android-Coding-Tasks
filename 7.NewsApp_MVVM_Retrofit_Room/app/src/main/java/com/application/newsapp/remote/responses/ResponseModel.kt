package com.application.newsapp.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("articles")
    val articleModels: List<ArticleModel>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)