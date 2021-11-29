package com.application.discovermovies.ui.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.application.discovermovies.R
import com.application.discovermovies.databinding.FragmentHomeBinding
import com.application.discovermovies.extras.Constants.MOVIE_VIEW_TYPE
import com.application.discovermovies.remote.NetworkState
import com.application.discovermovies.remote.OnItemClicked
import com.application.discovermovies.remote.responses.ResultModel
import com.application.discovermovies.ui.adapter.MoviesAdapter
import com.application.discovermovies.viewmodels.AppViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), OnItemClicked {

    lateinit var homeBinding: FragmentHomeBinding
    val viewModel: AppViewModels by viewModels()
    lateinit var adapter: MoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(requireContext(), this)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = adapter.getItemViewType(position)
                return if (viewType == MOVIE_VIEW_TYPE) 1
                else 3
            }
        }

        homeBinding.apply {
            rvHomeFragment.layoutManager = gridLayoutManager
            rvHomeFragment.setHasFixedSize(true)
            rvHomeFragment.adapter = adapter
        }


        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            if (it != null){
                adapter.submitList(it)
                swipeToRefresh.isRefreshing = false
            }

        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            homeBinding.progressBarPopular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            homeBinding.txtErrorPopular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                adapter.setNetworkState(it)
            }
            swipeToRefresh.isRefreshing = false
        })
    }


    override fun onItemClicked(resultModel: ResultModel?) {
        val bundle = Bundle()
        bundle.putSerializable("resultModel", resultModel)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
    }


}