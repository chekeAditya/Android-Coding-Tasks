package com.example.unit_5assignment.ui.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unit_5assignment.di.Module
import com.example.unit_5assignment.remote.responses.TVMazeResponseModel
import com.example.unit_5assignment.remote.responses.TVMazeResponseModelItem

class TvMazePagingSource : PagingSource<Int, TVMazeResponseModelItem>() {

    private val apiClient = Module.provideAPIService()

    override fun getRefreshKey(state: PagingState<Int, TVMazeResponseModelItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVMazeResponseModelItem> {
        val pageNumber = params.key ?: 1;
        val tvMazeResponseModel: TVMazeResponseModel = apiClient.getResponse(pageNumber)
        val tvMazeResponseModelItem: ArrayList<TVMazeResponseModelItem> = tvMazeResponseModel

        return try {
            LoadResult.Page(
                data = tvMazeResponseModelItem,
                prevKey = null,
                nextKey = if (tvMazeResponseModelItem.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}