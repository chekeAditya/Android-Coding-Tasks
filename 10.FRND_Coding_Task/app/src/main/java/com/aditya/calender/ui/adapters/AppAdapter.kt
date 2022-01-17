package com.aditya.calender.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.calender.R
import com.aditya.calender.databinding.ItemLayoutBinding
import com.aditya.calender.remote.interfaces.OnDateClicked

@RequiresApi(Build.VERSION_CODES.O)
class AppAdapter(
    private val daysList: ArrayList<String>,
    private val onDateClicked: OnDateClicked
) : RecyclerView.Adapter<AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout, parent, false
        )
        return AppViewHolder(itemLayoutBinding, onDateClicked)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.itemLayoutBinding.tvDates.setOnClickListener {
            onDateClicked.onDateClicked(position, holder.itemLayoutBinding.tvDates.text.toString())
        }
        holder.itemLayoutBinding.tvDates.text = daysList[position]
    }

    override fun getItemCount(): Int {
        return daysList.size
    }
}

@RequiresApi(Build.VERSION_CODES.O)
class AppViewHolder(
    public val itemLayoutBinding: ItemLayoutBinding,
    private val onDateClicked: OnDateClicked
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

}

