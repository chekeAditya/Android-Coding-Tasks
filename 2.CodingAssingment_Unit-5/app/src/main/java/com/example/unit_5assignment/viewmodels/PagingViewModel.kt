package com.example.unit_5assignment.viewmodels

import androidx.lifecycle.ViewModel
import com.example.unit_5assignment.repositorys.PagingRepo

class PagingViewModel : ViewModel() {

    private val pagingRepo = PagingRepo()

    fun searchTvMaje() = pagingRepo.getPagesIst()

}