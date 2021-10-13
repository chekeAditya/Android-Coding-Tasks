package com.example.customisablepizzas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customisablepizzas.R
import com.example.customisablepizzas.databinding.FragmentBottomSheetBinding
import com.example.customisablepizzas.databinding.FragmentListBinding
import com.example.customisablepizzas.remote.local.CartTable
import com.example.customisablepizzas.remote.response.CrustModel
import com.example.customisablepizzas.remote.response.SizeModel
import com.example.customisablepizzas.ui.adapter.ListViewAdapter
import com.example.customisablepizzas.ui.adapter.OnButtonClickedListeners
import com.example.customisablepizzas.ui.adapter.SelectSizeAdapter
import com.example.customisablepizzas.viewmodel.PizzaViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

@AndroidEntryPoint
class ListFragment : Fragment(), OnButtonClickedListeners {

    private lateinit var bottonSheetBinding: FragmentBottomSheetBinding
    lateinit var selectSizeAdapter: SelectSizeAdapter


    private lateinit var fragmentListBinding: FragmentListBinding
    lateinit var listViewAdapter: ListViewAdapter
    private val crustList = mutableListOf<CrustModel>()
    private val sizeList = mutableListOf<SizeModel>()
    private val pizzaViewModel: PizzaViewModel by viewModels()

    var count: Int = 0;
    var sum: Int = 0;
    var dataStored = CartTable("", "", 0.0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return fragmentListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        apiCalling()
//        btnContinue.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_bottomSheetFragment_to_orderSucceed)
//        }
    }

    private fun apiCalling() {
        pizzaViewModel.getCrustDataFromAPI().observe(viewLifecycleOwner, Observer {
            crustList.clear()
            crustList.addAll(it)
            listViewAdapter.notifyDataSetChanged()
        })
    }

    private fun setRecyclerView() {
        listViewAdapter = ListViewAdapter(sizeList, crustList, this)
        fragmentListBinding.apply {
            rvPizzaList.layoutManager = LinearLayoutManager(requireContext())
            rvPizzaList.adapter = listViewAdapter

        }
    }

    override fun onOpenBottomSheetClicked(crustModel: CrustModel) {
//        Navigation.findNavController(requireView()).navigate(R.id.action_listFragment_to_bottomSheetFragment) //navigation to anto
        bottonSheetBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
        val bottomDialog = BottomSheetDialog(
            requireContext(), R.style.BottomSheetDialogTheme
        )
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.fragment_bottom_sheet, bottonSheetBinding.rlBottomSheet
        )
        val listonclick = arrayListOf<SizeModel>()
        listonclick.addAll(crustModel.sizeModels)

        bottonSheetBinding.rvShowSize.apply {
            adapter = SelectSizeAdapter(listonclick,this@ListFragment)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        bottomDialog.setContentView(bottomSheetView)
        bottomDialog.show()
    }

    override fun onAddClicked(sizeModel: SizeModel) {
        count++
        bottonSheetBinding.displayQuantity.text = "Quantity ${count.toString()}"
        sum += sizeModel.price.toInt()
        bottonSheetBinding.totalSum.text = "Amount ${sum.toString()}"
        if (count > 0) {
            bottonSheetBinding.totalSum.visibility = View.VISIBLE
            bottonSheetBinding.displayQuantity.visibility = View.VISIBLE
        } else {
            bottonSheetBinding.totalSum.visibility = View.INVISIBLE
            bottonSheetBinding.displayQuantity.visibility = View.INVISIBLE
        }
        Toast.makeText(context, "Added Successful", Toast.LENGTH_SHORT).show()
        dataStored.pricePizza = sizeModel.price
        dataStored.namePizza = sizeModel.name
        pizzaViewModel.addPizzaToDB(dataStored)
    }

    override fun onRemoveClicked(sizeModel: SizeModel) {
        count--
        sum -= sizeModel.price.toInt()
        bottonSheetBinding.displayQuantity.text ="Quantity ${count.toString()}"
        bottonSheetBinding.totalSum.text = "Amount ${sum.toString()}"
        Toast.makeText(context, "Item Removed Successful", Toast.LENGTH_SHORT).show()
    }
}