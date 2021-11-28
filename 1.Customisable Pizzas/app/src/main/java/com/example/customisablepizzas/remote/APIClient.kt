package com.example.customisablepizzas.remote

import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    //URL :- https://gist.githubusercontent.com/chekeAditya/30bb6e2f20558100fe5fcf4c04e91892/raw/f4fbe0fbb1b081834e790e30e15be531f858a527/Customizable%2520Pizza
    @GET("Customizable%2520Pizza")
    suspend fun getPizzaResponse() : MainPizzaResponse

    @GET("Customizable%2520Pizza")
    suspend fun getSizePizzaResponse() : CrustModel

}