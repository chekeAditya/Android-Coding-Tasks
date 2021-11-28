package com.application.discovermovies.ui.movieDetailsFragment

import androidx.lifecycle.LiveData
import com.application.discovermovies.remote.APIClient
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.responses.ResultModel
import com.application.discovermovies.repositories.MovieDetailsDataNetworkSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMovieDetailsRepository @Inject constructor(
    private val apiClient: APIClient,
) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsDataNetworkSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<ResultModel> {

        movieDetailsNetworkDataSource = MovieDetailsDataNetworkSource(apiClient,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}