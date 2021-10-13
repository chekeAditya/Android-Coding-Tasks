package com.example.customisablepizzas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.ItemLayoutBinding
import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel

class ListViewAdapter(
    val sizeModelList: List<SizeModel>,
    private val crustModelList: List<CrustModel>,
    private val onBottomSheetClicked: OnButtonClickedListeners
) : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return ListViewHolder(itemLayoutBinding, onBottomSheetClicked)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val size = crustModelList[position].sizeModels[position]
        holder.onBindSizeResponse(size)

        val crust = crustModelList[position]
        holder.onBindCrust(crust)
    }

    override fun getItemCount(): Int {
        return crustModelList.size
    }

    //viewHolder
    class ListViewHolder(
        private val itemLayoutBinding: ItemLayoutBinding,
        val onBottomSheetClicked: OnButtonClickedListeners
    ) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun onBindSizeResponse(sizeModel: SizeModel) {
            itemLayoutBinding.size = sizeModel
        }

        fun onBindCrust(crustModel: CrustModel) {
            itemLayoutBinding.addItemClicked = onBottomSheetClicked
            itemLayoutBinding.crust = crustModel
        }
    }
}