package com.example.unit_5assignment.viewmodels

import androidx.lifecycle.ViewModel
import com.example.unit_5assignment.repositorys.PagingRepo
import com.example.unit_5assignment.repositorys.Repository

class DataViewModel(val repo: Repository):ViewModel() {

    fun addDataToDB(){
        repo.addDataToDB()
    }
}