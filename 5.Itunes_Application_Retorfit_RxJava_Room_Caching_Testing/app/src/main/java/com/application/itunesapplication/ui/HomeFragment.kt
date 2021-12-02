package com.application.itunesapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.application.itunesapplication.remote.db.ItunesDbTable
import com.application.itunesapplication.remote.responses.ResultModel
import com.application.itunesapplication.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        bindingHomeFragment.etEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                bindingHomeFragment.etEditText.clearFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApi(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    fun loadApi(query: String) {
        viewModel.getDataFromAPI(query).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> {
                    viewModel.getDataFromDb()
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
                    CoroutineScope(Dispatchers.Default).launch {
                        insertDataToDb(it.data!!.resultModels)
                    }
                }
            }
        })
        viewModel.getDataFromAPI(query)
    }

    private fun insertDataToDb(resultModels: List<ResultModel>) {
        viewModel.deleteDataFromDb()
        resultModels.forEach {
            val itunesDb = ItunesDbTable(it.artistName, it.artworkUrl100)
            viewModel.insertDataInDb(itunesDb)
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