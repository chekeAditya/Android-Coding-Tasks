package com.example.customisablepizzas.remote.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PizzaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataToCart(cartTable: CartTable)

    @Query("select * from PizzaCart")
    fun getDataFromPizzaCart(): LiveData<List<CartTable>>

    @Query("select * from PizzaCart where namePizza=:namePizza and sizePizza=:sizePizza")
    fun getSelectedPizza(namePizza: String, sizePizza: String): List<CartTable>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(cartTable: CartTable)

    @Delete()
    fun deletePizza(cartTable: CartTable)
}