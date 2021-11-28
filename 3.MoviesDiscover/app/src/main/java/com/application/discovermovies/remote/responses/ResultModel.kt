package com.application.discovermovies.remote.responses


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultModel(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: Any,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: String,
    @SerializedName("poster_path")
    val posterPath: Any,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String
) : Serializable