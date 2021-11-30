package com.application.addresssearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.addresssearch.R
import com.application.addresssearch.adapters.CityAdapter
import com.application.addresssearch.databinding.FragmentHomeBinding
import com.application.addresssearch.remote.responses.AddressModel
import com.application.addresssearch.viewmodels.AppViewModel
import com.arlib.floatingsearchview.FloatingSearchView.OnQueryChangeListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: AppViewModel
    lateinit var homeFragmentHomeBinding: FragmentHomeBinding
    lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeFragmentHomeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = AppViewModel()
        searchEditText()
        setRecyclerView()
    }

    private fun setRecyclerView() {

        homeFragmentHomeBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            cityAdapter = CityAdapter()
            adapter = cityAdapter
        }
    }

    private fun searchEditText() {

        homeFragmentHomeBinding.etCityName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApiData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

//        homeFragmentHomeBinding.floatingSearchView.setOnQueryChangeListener(OnQueryChangeListener { _, newQuery -> //get suggestions based on newQuery
//            loadApiData(newQuery)
//        })
    }

    fun loadApiData(input: String) {
        viewModel.getCityListObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                cityAdapter.addressList = it.dataModel.addressModelList as ArrayList<AddressModel>
                cityAdapter.notifyDataSetChanged()
            }
        })
        viewModel.makeApiCall(input)
    }


}