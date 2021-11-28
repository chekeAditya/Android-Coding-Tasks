package com.application.discovermovies.di

import com.application.discovermovies.extras.Constants.API_KEY
import com.application.discovermovies.extras.Constants.BASE_URL
import com.application.discovermovies.extras.Constants.TIMEOUT_TIME
import com.application.discovermovies.remote.APIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): APIClient {

        val requestInterceptor = Interceptor {
            val url = it.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor it.proceed(request)
        }


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIClient::class.java)
    }



}