package com.application.addresssearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.addresssearch.R
import com.application.addresssearch.databinding.ItemLayoutBinding
import com.application.addresssearch.remote.responses.AddressModel

class CityAdapter(
) : RecyclerView.Adapter<CityViewHolder>() {

    var addressList = ArrayList<AddressModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return CityViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(addressList[position])
    }

    override fun getItemCount(): Int {
        return addressList.size
    }
}

class CityViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun bind(addressModel: AddressModel) {
        itemLayoutBinding.addressList = addressModel
    }
}