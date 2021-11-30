package com.application.discovermovies.remote

import com.application.discovermovies.remote.responses.MovieResponseModel
import com.application.discovermovies.remote.responses.ResultModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {

    //BASE_URL :- https://api.themoviedb.org/3/discover/movie?api_key=328c283cd27bd1877d9080ccb1604c91&primary_release_date.lte=2016-12-31&sort_by=release_date.desc&page=1

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("primary_release_date") primary_release_date: String
    ): Single<MovieResponseModel>


}