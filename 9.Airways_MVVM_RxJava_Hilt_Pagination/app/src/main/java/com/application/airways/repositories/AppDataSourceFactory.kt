package com.application.airways.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.application.airways.remote.APIClient
import com.application.airways.remote.respnses.AirlineModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataSourceFactory @Inject constructor(
    private val apiCList: APIClient,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, AirlineModel>() {

    private val appLiveDataSource = MutableLiveData<AppDataSource>()

    override fun create(): DataSource<Int, AirlineModel> {
        val appDataSource = AppDataSource(apiCList, compositeDisposable)

        appLiveDataSource.postValue(appDataSource)
        return appDataSource
    }
}