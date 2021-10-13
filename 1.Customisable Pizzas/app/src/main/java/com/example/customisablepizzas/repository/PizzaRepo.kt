package com.example.customisablepizzas.repository

import androidx.lifecycle.LiveData
import com.example.customisablepizzas.remote.APIClient
import com.example.customisablepizzas.remote.local.CartTable
import com.example.customisablepizzas.remote.local.PizzaDao
import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


class PizzaRepo @Inject constructor(private val dao: PizzaDao, private val apiClient: APIClient) {

    suspend fun getCrustDataFromAPI(): List<CrustModel> {
        return apiClient.getPizzaResponse().crustModels
    }

    suspend fun getSizePizzaResponse(): List<SizeModel> {
        return apiClient.getSizePizzaResponse().sizeModels
    }

    fun insertData(cartTable: CartTable) {
        CoroutineScope(IO).launch {
            val listResponse = dao.getSelectedPizza(cartTable.namePizza, cartTable.sizePizza)
            if (listResponse.isNotEmpty()){
                val oldData = listResponse[0]
                oldData.pricePizza = oldData.pricePizza + cartTable.pricePizza
                dao.updateData(oldData)
            }else
                dao.insertDataToCart(cartTable)
        }
    }

    fun getInsertedDataFromDB() : LiveData<List<CartTable>>{
        return dao.getDataFromPizzaCart()
    }
}
