package com.example.unit_5assignment.remote

import com.example.unit_5assignment.remote.responses.TVMazeResponseModel
import retrofit2.http.GET

interface APIClient {

    //url :- https://api.tvmaze.com/people?page=1
    @GET("people?page=1")
    suspend fun getResponse() : TVMazeResponseModel
}