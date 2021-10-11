package com.example.customisablepizzas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.ItemLayoutBinding
import com.example.customisablepizzas.remote.response.Crust
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import com.example.customisablepizzas.remote.response.Size

class ListViewAdapter(
    val sizeList : List<Size>,
    val crustList : List<Crust>
) : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return ListViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      val size = crustList[position].sizes[position]
        holder.onBindSizeResponse(size)

        val crust = crustList[position]
        holder.onBindCrust(crust)
    }

    override fun getItemCount(): Int {
        return crustList.size
    }

    //viewHolder
    class ListViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun onBindSizeResponse(size: Size) {
            itemLayoutBinding.size = size
        }

        fun onBindCrust(crust: Crust) {
            itemLayoutBinding.crust = crust
        }
    }
}