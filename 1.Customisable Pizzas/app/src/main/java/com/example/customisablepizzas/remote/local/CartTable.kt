package com.example.customisablepizzas.remote.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PizzaCart")
class CartTable(
    @ColumnInfo(name = "sizePizza")
    val sizePizza: String,
    @ColumnInfo(name = "namePizza")
    var namePizza: String,
    @ColumnInfo(name = "pricePizza")
    var pricePizza: Double,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
)