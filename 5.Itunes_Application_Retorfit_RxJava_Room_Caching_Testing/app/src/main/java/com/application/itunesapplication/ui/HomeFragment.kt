package com.application.itunesapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.application.itunesapplication.R
import com.application.itunesapplication.adapter.ItunesAdapter
import com.application.itunesapplication.databinding.FragmentHomeBinding
import com.application.itunesapplication.remote.Status
import com.application.itunesapplication.remote.responses.ResultModel
import com.application.itunesapplication.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var bindingHomeFragment: FragmentHomeBinding
    val viewModel: AppViewModel by viewModels()
    lateinit var itunesAdapter: ItunesAdapter
    val resultList = mutableListOf<ResultModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView();
        bindingHomeFragment.btnSearch.setOnClickListener {
            val text = bindingHomeFragment.etEditText.toString()
            viewModel.getDataFromAPI().observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.ERROR -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        resultList.clear()
                        it.data?.resultModels.let {
                            if (it != null) {
                                resultList.addAll(it)
                                itunesAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            })
            viewModel.getDataFromAPI()
        }
    }

    private fun setRecyclerView() {
        itunesAdapter = ItunesAdapter(resultList)
        bindingHomeFragment.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = itunesAdapter
        }
    }
}