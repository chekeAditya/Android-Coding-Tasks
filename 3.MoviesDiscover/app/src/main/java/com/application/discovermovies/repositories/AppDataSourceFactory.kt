package com.application.discovermovies.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.application.discovermovies.remote.APIClient
import com.application.discovermovies.remote.responses.ResultModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataSourceFactory @Inject constructor(
    private val apiClient: APIClient,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, ResultModel>() {

    val appLiveDataSource =  MutableLiveData<AppDataSource>()


    override fun create(): DataSource<Int, ResultModel> {
        val appDataSource = AppDataSource(apiClient, compositeDisposable)

        appLiveDataSource.postValue(appDataSource)
        return appDataSource
    }

}