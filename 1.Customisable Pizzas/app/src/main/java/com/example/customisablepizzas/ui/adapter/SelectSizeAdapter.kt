package com.example.customisablepizzas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.ItemLayoutSizeBinding
import com.example.customisablepizzas.remote.response.MainPizzaResponse
import com.example.customisablepizzas.remote.response.SizeModel
import com.example.customisablepizzas.ui.fragment.BottomSheetFragment

class SelectSizeAdapter(
    private val sizeModelList: List<SizeModel>,
    private val listeners: OnButtonClickedListeners
) : RecyclerView.Adapter<SelectSizeAdapter.SelectSizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSizeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLayoutSizeBinding: ItemLayoutSizeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout_size, parent, false)
        return SelectSizeViewHolder(itemLayoutSizeBinding,listeners)
    }

    override fun onBindViewHolder(holder: SelectSizeViewHolder, position: Int) {
        val sizeModel = sizeModelList[position]
        holder.onBindSizeResponse(sizeModel)
    }

    override fun getItemCount(): Int {
        return sizeModelList.size
    }

    //viewHolder
    class SelectSizeViewHolder(
        private val itemLayoutSizeBinding: ItemLayoutSizeBinding,
        private val listeners: OnButtonClickedListeners
    ) : RecyclerView.ViewHolder(itemLayoutSizeBinding.root) {

        fun onBindSizeResponse(sizeModel: SizeModel) {
            itemLayoutSizeBinding.onItemSelect = listeners
            itemLayoutSizeBinding.selectSize = sizeModel
        }
    }
}