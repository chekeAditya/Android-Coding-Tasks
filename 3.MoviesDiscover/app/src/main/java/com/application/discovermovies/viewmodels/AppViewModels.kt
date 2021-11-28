package com.application.discovermovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.responses.ResultModel
import com.application.discovermovies.ui.homeFragment.PopularMoviePagedListRepository
import com.application.discovermovies.ui.movieDetailsFragment.PopularMovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class AppViewModels @Inject constructor(
    private val popularMoviePagedListRepository: PopularMoviePagedListRepository,
    private val movieDetailsRepository: PopularMovieDetailsRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val movieId: Int = 0
    public var resultId: Int = 0

    val moviePagedList: LiveData<PagedList<ResultModel>> by lazy {
        popularMoviePagedListRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        popularMoviePagedListRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    val movieDetails: LiveData<ResultModel> by lazy {
        movieDetailsRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkDetailsState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}