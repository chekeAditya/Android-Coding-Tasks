package com.application.airways.repositories

import com.application.airways.remote.APIClient
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataSource @Inject constructor(
    private val apiClient: APIClient,
    private val compositeDisposable: CompositeDisposable
) : {


}