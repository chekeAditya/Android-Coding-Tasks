package com.example.customisablepizzas.repository

import android.util.Log
import com.example.customisablepizzas.remote.APIClient
import com.example.customisablepizzas.remote.response.Crust
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import javax.inject.Inject


class PizzaRepo @Inject constructor(private val apiClient: APIClient) {

    suspend fun getDataFromAPI(): List<Crust> {
        return apiClient.getPizzaResponse().crusts
    }

}
