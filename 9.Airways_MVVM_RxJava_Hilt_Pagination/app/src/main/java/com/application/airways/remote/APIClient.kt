package com.application.airways.remote

import com.application.airways.remote.respnses.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    // BASE_URL :- https://api.instantwebtools.net/v1/passenger?page=0&size=10

    @GET("v1/passenger")
    suspend fun getResponseFromAPI(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ResponseModel

}