package com.example.customisablepizzas.ui.adapter

import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel

interface OnButtonClickedListeners {

    fun onOpenBottomSheetClicked(crustModel: CrustModel)
    fun onAddClicked(sizeModel: SizeModel)
    fun onRemoveClicked(sizeModel: SizeModel)
}

