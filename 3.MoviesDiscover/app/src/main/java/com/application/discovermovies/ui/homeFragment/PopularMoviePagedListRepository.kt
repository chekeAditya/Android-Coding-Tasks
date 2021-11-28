package com.application.discovermovies.ui.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.application.discovermovies.extras.Constants.POST_PER_PAGE
import com.application.discovermovies.remote.APIClient
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.responses.ResultModel
import com.application.discovermovies.repositories.AppDataSource
import com.application.discovermovies.repositories.AppDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMoviePagedListRepository @Inject constructor(
    private val apiClient: APIClient
) {
    lateinit var moviePagedList: LiveData<PagedList<ResultModel>>
    lateinit var moviesDataSourceFactory: AppDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<ResultModel>> {
        moviesDataSourceFactory = AppDataSourceFactory(apiClient, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<AppDataSource, NetworkState>(
            moviesDataSourceFactory.appLiveDataSource, AppDataSource::networkState
        )
    }

}