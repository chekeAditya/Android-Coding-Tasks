package com.application.discovermovies.remote

import com.application.discovermovies.remote.responses.ResultModel

interface OnItemClicked {

    fun onItemClicked(resultModel: ResultModel?)

}