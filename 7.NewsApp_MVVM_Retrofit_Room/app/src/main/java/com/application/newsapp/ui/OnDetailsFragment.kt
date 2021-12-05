package com.application.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.application.newsapp.R
import com.application.newsapp.databinding.FragmentOnDetailsBinding
import com.bumptech.glide.Glide


class OnDetailsFragment : Fragment() {

    val args: OnDetailsFragmentArgs by navArgs()
    lateinit var bindingOnDetailsFragment: FragmentOnDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingOnDetailsFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_on_details, container, false)
        return bindingOnDetailsFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingOnDetailsFragment.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_onDetailsFragment_to_homeFragment)
        }
        Glide.with(bindingOnDetailsFragment.ivImageDetails).load(args.articleResult.urlToImage)
            .into(bindingOnDetailsFragment.ivImageDetails)
        bindingOnDetailsFragment.tvDescription.text = args.articleResult.description
        bindingOnDetailsFragment.tvTitle.text = args.articleResult.content
        bindingOnDetailsFragment.tvCardDate.text = args.articleResult.publishedAt
    }

}