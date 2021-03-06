package com.application.itunesapplication.remote.interfaces

import com.application.itunesapplication.remote.responses.ItunesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    //Base_Url:- https://itunes.apple.com/search?term=baby
    @GET("search")
    suspend fun getResponse(
        @Query("term") term: String
    ): ItunesResponseModel

//    @GET("search?term=baby")
//    suspend fun getResponse(): ItunesResponseModel

}