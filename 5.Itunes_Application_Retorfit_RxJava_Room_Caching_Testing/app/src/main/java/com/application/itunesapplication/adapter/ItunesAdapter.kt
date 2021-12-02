package com.application.itunesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.itunesapplication.R
import com.application.itunesapplication.databinding.ItemLayoutBinding
import com.application.itunesapplication.remote.responses.ResultModel
import com.bumptech.glide.Glide

class ItunesAdapter(
    private val resultList: List<ResultModel>
) : RecyclerView.Adapter<ItunesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout, parent, false
        )
        return ItunesViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ItunesViewHolder, position: Int) {
        val resultModel = resultList[position]
        holder.onBind(resultModel)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

}

class ItunesViewHolder(
    private val itemLayoutBinding: ItemLayoutBinding,
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBind(resultModel: ResultModel) {
//        itemLayoutBinding.artistName?.text = resultModel.artistName
        Glide.with(itemLayoutBinding.artistImage).load(resultModel.artworkUrl100)
            .into(itemLayoutBinding.artistImage)
    }

}