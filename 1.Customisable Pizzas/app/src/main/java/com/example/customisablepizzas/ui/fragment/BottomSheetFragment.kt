package com.example.customisablepizzas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.FragmentBottomSheetBinding
import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel
import com.example.customisablepizzas.ui.adapter.ListViewAdapter
import com.example.customisablepizzas.ui.adapter.OnButtonClickedListeners
import com.example.customisablepizzas.ui.adapter.SelectSizeAdapter
import com.example.customisablepizzas.viewmodel.PizzaViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment(),OnButtonClickedListeners {


    lateinit var listViewAdapter: ListViewAdapter
    private val crustList = mutableListOf<CrustModel>()
    private lateinit var bottonSheetBinding: FragmentBottomSheetBinding
    private val pizzaViewModel: PizzaViewModel by viewModels()
    var sizeList = mutableListOf<SizeModel>()
    var crustModel = mutableListOf<CrustModel>()
    lateinit var selectSizeAdapter: SelectSizeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottonSheetBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        return bottonSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectSizeAdapter = SelectSizeAdapter(sizeList,this)
        apiCalling()
    }

    private fun apiCalling() {
        pizzaViewModel.getCrustDataFromAPI().observe(viewLifecycleOwner, Observer {
            crustList.clear()
            crustList.addAll(it)
            selectSizeAdapter.notifyDataSetChanged()
        })
    }

    override fun onOpenBottomSheetClicked(crustModel: CrustModel) {
        val listenOnClick = arrayListOf<SizeModel>()
        listenOnClick.addAll(crustModel.sizeModels)
        selectSizeAdapter = SelectSizeAdapter(listenOnClick,this)
        bottonSheetBinding.apply {
            rvShowSize.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvShowSize.adapter = selectSizeAdapter
        }
    }

    override fun onAddClicked(sizeModel: SizeModel) {
    }

    override fun onRemoveClicked(sizeModel: SizeModel) {
    }

}