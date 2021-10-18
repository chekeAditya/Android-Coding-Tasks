package com.example.unit_5assignment.remote

import com.example.unit_5assignment.remote.responses.TVMazeResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    //url :- https://api.tvmaze.com/people?page=1
    @GET("people")
    suspend fun getResponse(@Query("page") page : Int) : TVMazeResponseModel
}