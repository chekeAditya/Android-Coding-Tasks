package com.application.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.newsapp.R
import com.application.newsapp.databinding.ItemLayoutBinding
import com.application.newsapp.remote.OnCardClicked
import com.application.newsapp.remote.responses.ArticleModel
import com.bumptech.glide.Glide

class NewsAdapter(
    private val articleModelList: List<ArticleModel>,
    private val OnCardClicked: OnCardClicked
) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout, parent, false
            )
        return NewsViewHolder(itemLayoutBinding, OnCardClicked)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articleModel = articleModelList[position]
        holder.onBind(articleModel)
    }

    override fun getItemCount(): Int {
        return articleModelList.size
    }
}

class NewsViewHolder(
    private val itemLayoutBinding: ItemLayoutBinding,
    private val OnCardClicked: OnCardClicked
) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBind(articleModel: ArticleModel) {
        itemLayoutBinding.onCardClicked = OnCardClicked
        itemLayoutBinding.article = articleModel
        Glide.with(itemLayoutBinding.iCard).load(articleModel.urlToImage)
            .into(itemLayoutBinding.iCard)
    }

}
