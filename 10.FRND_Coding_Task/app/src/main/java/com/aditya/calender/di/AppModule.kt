package com.aditya.calender.di

import com.aditya.calender.remote.interfaces.APIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun ProvidesAPi(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl("http://13.232.92.136:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }


}