package com.example.customisablepizzas.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartTable::class], version = 1)
abstract class PizzaDatabase :RoomDatabase(){

    abstract fun getResponsePizzaDao() : PizzaDao
}