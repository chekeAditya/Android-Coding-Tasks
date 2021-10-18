package com.example.unit_5assignment.repositorys

import com.example.unit_5assignment.remote.APIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(private val apiClient: APIClient) {


    fun getResponseFromAPI(){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

}