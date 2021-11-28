package com.application.discovermovies.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.discovermovies.remote.APIClient
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.responses.ResultModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsDataNetworkSource @Inject constructor(
    private val apiClient: APIClient,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedMovieDetailsResponse = MutableLiveData<ResultModel>()
    val downloadedMovieResponse: LiveData<ResultModel>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiClient.getDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("Aditya", "MovieDetailsDataSource ${it.message}")
                        }
                    )
            )

        } catch (e: Exception) {
            Log.e("Aditya", "MovieDetailsDataSource: ${e.message}")
        }
    }

}