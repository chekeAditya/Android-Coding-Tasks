package com.example.unit_5assignment.repositorys

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.unit_5assignment.remote.APIClient
import com.example.unit_5assignment.ui.adapter.TvMazePagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PagingRepo() {

//    private val apiClient = APIClient()
//    fun getResponseFromAPI() {
//        CoroutineScope(IO).launch {
//            saveToDB()
//        }
//    }

    fun getPagesIst() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = { TvMazePagingSource() }
        ).liveData
}