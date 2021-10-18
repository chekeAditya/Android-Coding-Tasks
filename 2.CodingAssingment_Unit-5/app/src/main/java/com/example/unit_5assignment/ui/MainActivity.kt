package com.example.unit_5assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.unit_5assignment.R
import com.example.unit_5assignment.databinding.ActivityMainBinding
import com.example.unit_5assignment.databinding.ItemLayoutBinding
import com.example.unit_5assignment.ui.adapter.TvMazeAdapter
import com.example.unit_5assignment.viewmodels.PagingViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var pagingViewModel: PagingViewModel
    private lateinit var pagingAdapter: TvMazeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.apply {

        }
        pagingViewModel = ViewModelProvider(this).get(PagingViewModel::class.java)
        setAdapter()
    }

    private fun setAdapter() {
        pagingAdapter = TvMazeAdapter()

    }
}