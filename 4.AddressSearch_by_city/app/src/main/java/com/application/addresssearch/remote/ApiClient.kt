package com.application.addresssearch.remote

import com.application.addresssearch.remote.responses.DataModel
import com.application.addresssearch.remote.responses.ResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    //Base_Url :- https://digi-api.airtel.in/compassLocation/rest/address/autocomplete?queryString=airtel&city=gurgaon


    @GET("compassLocation/rest/address/autocomplete")
    fun getResponse(
        @Query("queryString") queryString: String,
        @Query("city") city: String
    ): Observable<ResponseDTO>
//    fun getResponse(): Observable<DataModel>
}