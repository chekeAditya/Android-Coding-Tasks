package com.example.unit_5assignment.ui.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unit_5assignment.R
import com.example.unit_5assignment.databinding.ItemLayoutBinding
import com.example.unit_5assignment.remote.responses.TVMazeResponseModelItem

class TvMazeAdapter : PagingDataAdapter<TVMazeResponseModelItem, TvMazeViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TVMazeResponseModelItem>() {
            override fun areItemsTheSame(
                oldItem: TVMazeResponseModelItem,
                newItem: TVMazeResponseModelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TVMazeResponseModelItem,
                newItem: TVMazeResponseModelItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: TvMazeViewHolder, position: Int) {
        val tvMazeResponseModelItem = getItem(position)
        tvMazeResponseModelItem?.let {
            if (!(it.deathday == null || it.birthday == null)) {
                holder.onBindTvMazeItem(it)
            }else{
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvMazeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return TvMazeViewHolder(itemLayoutBinding)
    }
}

//viewHolder
class TvMazeViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBindTvMazeItem(tvMazeResponseModelItem: TVMazeResponseModelItem?) {
        itemLayoutBinding.responseItem = tvMazeResponseModelItem
        try {
            Glide.with(itemLayoutBinding.ivImage).load(tvMazeResponseModelItem?.image?.original)
                .into(itemLayoutBinding.ivImage)

        } catch (e: Exception) {
            Log.d("TAG", "onBindTvMazeItem: ${e.printStackTrace()}")
        }
    }
}