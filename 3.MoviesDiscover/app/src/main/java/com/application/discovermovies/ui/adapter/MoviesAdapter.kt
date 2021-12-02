package com.application.discovermovies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.discovermovies.R
import com.application.discovermovies.databinding.ItemLayoutBinding
import com.application.discovermovies.databinding.NetworkStateItemBinding
import com.application.discovermovies.extras.Constants.MOVIE_VIEW_TYPE
import com.application.discovermovies.extras.Constants.NETWORK_VIEW_TYPE
import com.application.discovermovies.extras.Constants.URL_IMAGE
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.NetworkState.Companion.END_OF_LIST
import com.application.discovermovies.remote.OnItemClicked
import com.application.discovermovies.remote.responses.ResultModel
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val context: Context,
    private val onItemClicked: OnItemClicked
) : PagedListAdapter<ResultModel, RecyclerView.ViewHolder>(diffutil) {

    var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == MOVIE_VIEW_TYPE) {
            val view: ItemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout,
                parent,
                false
            )
            return MoviesViewHolder(view, onItemClicked)
        } else {
            val view: NetworkStateItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.network_state_item,
                parent,
                false
            )
            return LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MoviesViewHolder).onBind(getItem(position))
        } else {
            (holder as LoadingViewHolder).onBind(networkState = networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }

    companion object {
        val diffutil = object : DiffUtil.ItemCallback<ResultModel>() {
            override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    @JvmName("setNetworkState1")
    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

}

class MoviesViewHolder(
    private val itemLayoutBinding: ItemLayoutBinding,
    private val onItemClicked: OnItemClicked
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBind(resultModel: ResultModel?) {
        itemLayoutBinding.data = resultModel
        itemLayoutBinding.onItemClicked = onItemClicked

        //used for setting up the image prachi
        Glide.with(itemLayoutBinding.ivMovieImage)
            .load(URL_IMAGE + resultModel?.posterPath)
            .into(itemLayoutBinding.ivMovieImage)
    }
}

class LoadingViewHolder(private val itemNetworkStateBinding: NetworkStateItemBinding) :
    RecyclerView.ViewHolder(itemNetworkStateBinding.root) {

    fun onBind(networkState: NetworkState?) {
        if (networkState != null && networkState == NetworkState.LOADING)
            itemNetworkStateBinding.progressBarItem.visibility = View.VISIBLE;
        else
            itemNetworkStateBinding.progressBarItem.visibility = View.GONE;

        if (networkState != null && networkState == NetworkState.ERROR) {
            itemNetworkStateBinding.progressBarItem.visibility = View.VISIBLE;
            itemNetworkStateBinding.errorMsgItem.text = networkState.msg;
        } else if (networkState != null && networkState == END_OF_LIST) {
            itemNetworkStateBinding.errorMsgItem.visibility = View.VISIBLE;
            itemNetworkStateBinding.errorMsgItem.text = networkState.msg;
        } else
            itemNetworkStateBinding.errorMsgItem.visibility = View.GONE;
    }
}


/*
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    val view: ItemLayoutBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
    return MoviesViewHolder(view)
}

override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    val resultModel = responseList[position]
    holder.onBind(resultModel)
}

override fun getItemCount(): Int {
    return responseList.size
}
*/
