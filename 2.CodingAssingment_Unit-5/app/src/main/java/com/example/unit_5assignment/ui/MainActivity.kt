package com.example.unit_5assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unit_5assignment.R
import com.example.unit_5assignment.databinding.ActivityMainBinding
import com.example.unit_5assignment.ui.adapter.TvMazeAdapter
import com.example.unit_5assignment.viewmodels.PagingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var pagingViewModel: PagingViewModel
    private lateinit var pagingAdapter: TvMazeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        pagingViewModel = ViewModelProvider(this).get(PagingViewModel::class.java)
        setAdapter()

        pagingViewModel.searchTvMaje().observe(this, Observer {
            it?.let {
                CoroutineScope(IO).launch {
                    pagingAdapter.submitData(it)
                }
            }
        })
    }

    private fun setAdapter() {
        pagingAdapter = TvMazeAdapter()
        val linearLayout = LinearLayoutManager(this)
        activityMainBinding.recyclerView.apply {
            adapter = pagingAdapter
            layoutManager = linearLayout
        }
    }
}