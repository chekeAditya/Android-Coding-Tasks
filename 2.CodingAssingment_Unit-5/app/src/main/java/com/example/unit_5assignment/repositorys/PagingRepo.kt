package com.example.unit_5assignment.repositorys

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.unit_5assignment.remote.responses.TVMazeResponseModelItem
import com.example.unit_5assignment.ui.adapter.TvMazePagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PagingRepo () {

    fun getResponseFromAPI(){
        CoroutineScope(IO).launch {
//            saveToDB(getPagesIst())
        }
    }


    fun getPagesIst() =
        Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { TvMazePagingSource() }
        ).liveData
}