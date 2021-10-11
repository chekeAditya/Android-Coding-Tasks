package com.example.customisablepizzas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.FragmentListBinding
import com.example.customisablepizzas.di.PizzaModule
import com.example.customisablepizzas.remote.response.Crust
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import com.example.customisablepizzas.remote.response.Size
import com.example.customisablepizzas.repository.PizzaRepo
import com.example.customisablepizzas.ui.adapter.ListViewAdapter
import com.example.customisablepizzas.viewmodel.PizzaViewModel
import com.example.customisablepizzas.viewmodel.PizzaViewModelFactory

class ListFragment : Fragment() {

    private lateinit var fragmentListBinding: FragmentListBinding
    lateinit var listViewAdapter: ListViewAdapter
    private val sizeList = mutableListOf<Size>()
    private val crustList = mutableListOf<Crust>()
    lateinit var pizzaViewModel: PizzaViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return fragmentListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pizzaViewModel = ViewModelProviders.of(
            this,
            PizzaViewModelFactory(PizzaRepo(PizzaModule.provideAPISERVICE()))
        )
            .get(PizzaViewModel::class.java)

        fragmentListBinding.apply {
            listViewAdapter = ListViewAdapter(sizeList,crustList)
            rvPizzaList.layoutManager = LinearLayoutManager(requireContext())
            rvPizzaList.adapter = listViewAdapter
        }

        pizzaViewModel.getDataFromAPI().observe(viewLifecycleOwner, Observer {
            crustList.clear()
            crustList.addAll(it)
            listViewAdapter.notifyDataSetChanged()
        })
    }
}