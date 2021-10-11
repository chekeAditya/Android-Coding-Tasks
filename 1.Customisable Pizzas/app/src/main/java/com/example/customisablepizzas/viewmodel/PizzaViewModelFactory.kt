package com.example.customisablepizzas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customisablepizzas.repository.PizzaRepo

class PizzaViewModelFactory(val pizzaRepo: PizzaRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PizzaViewModel(pizzaRepo) as T
    }

}