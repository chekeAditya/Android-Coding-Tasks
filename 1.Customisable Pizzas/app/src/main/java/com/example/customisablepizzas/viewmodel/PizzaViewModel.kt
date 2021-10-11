package com.example.customisablepizzas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.customisablepizzas.remote.response.Crust
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import com.example.customisablepizzas.repository.PizzaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(val pizzaRepo: PizzaRepo) : ViewModel() {


    fun getDataFromAPI(): LiveData<List<Crust>> {
        return liveData(Dispatchers.IO) {
            emit(pizzaRepo.getDataFromAPI())
        }
    }
}