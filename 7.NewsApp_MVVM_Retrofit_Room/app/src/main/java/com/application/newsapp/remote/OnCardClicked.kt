package com.application.newsapp.remote

import com.application.newsapp.remote.responses.ArticleModel

interface OnCardClicked {

    fun onCardClicked(articleModel: ArticleModel)

}