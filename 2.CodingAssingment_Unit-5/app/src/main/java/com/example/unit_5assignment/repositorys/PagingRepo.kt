package com.example.unit_5assignment.repositorys

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.unit_5assignment.ui.adapter.TvMazePagingSource

class PagingRepo {

    fun getPagesIst() =
        Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { TvMazePagingSource() }
        ).liveData
}