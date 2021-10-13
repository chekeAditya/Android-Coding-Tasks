package com.example.customisablepizzas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.customisablepizzas.remote.local.CartTable
import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel
import com.example.customisablepizzas.repository.PizzaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(val pizzaRepo: PizzaRepo) : ViewModel() {


    fun getCrustDataFromAPI(): LiveData<List<CrustModel>> {
        return liveData(Dispatchers.IO) {
            emit(pizzaRepo.getCrustDataFromAPI())
        }
    }

    fun getSizeDataFromAPI(): LiveData<List<SizeModel>> {
        return liveData(Dispatchers.IO) {
            emit(pizzaRepo.getSizePizzaResponse())
        }
    }

    fun addPizzaToDB(cartTable: CartTable){
        pizzaRepo.insertData(cartTable)
    }

    fun getPizzaFromDB(cartTable: CartTable) : LiveData<List<CartTable>> {
        return pizzaRepo.getInsertedDataFromDB()
    }
}