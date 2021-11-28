package com.application.discovermovies.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.application.discovermovies.extras.Constants.FIRST_PAGE
import com.application.discovermovies.remote.APIClient
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.responses.ResultModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AppDataSource @Inject constructor(
    private val apiClient: APIClient,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, ResultModel>() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    private var page = FIRST_PAGE
    private var date = Calendar.getInstance().time
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultModel>
    ) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiClient.getPopularMovies(page, dateFormat.format(date))
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.resultModels, null, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("Aditya", "loadInitial: ${it.message}")
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiClient.getPopularMovies(params.key,dateFormat.format(date))
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.totalPages >= params.key) {
                            callback.onResult(it.resultModels, params.key + 1)
                            networkState.postValue(NetworkState.LOADED)
                        } else {
                            networkState.postValue(NetworkState.END_OF_LIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("Aditya", "loadBefore: ${it.message}")
                    }
                )
        )
    }

}