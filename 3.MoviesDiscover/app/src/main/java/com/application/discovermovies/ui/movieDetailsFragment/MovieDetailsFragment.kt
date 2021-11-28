package com.application.discovermovies.ui.movieDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.discovermovies.R
import com.application.discovermovies.databinding.FragmentMovieDetailsBinding
import com.application.discovermovies.extras.Constants.URL_IMAGE
import com.application.discovermovies.remote.responses.ResultModel
import com.application.discovermovies.viewmodels.AppViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    lateinit var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding
    val viewModel: AppViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return fragmentMovieDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = Bundle()
        val resultModel: ResultModel? = arguments?.getSerializable("resultModel") as ResultModel?

        bindUI(resultModel)

    }

    private fun bindUI(resultModel: ResultModel?) {
        fragmentMovieDetailsBinding.movieTitle.text = resultModel?.title
        fragmentMovieDetailsBinding.movieLanguage.text = resultModel?.originalLanguage
        fragmentMovieDetailsBinding.moviePopularity.text = resultModel?.popularity
        fragmentMovieDetailsBinding.movieReleaseDate.text = resultModel?.releaseDate
        fragmentMovieDetailsBinding.movieOverview.text = resultModel?.overview
        val moviePosterURL = URL_IMAGE + resultModel?.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(fragmentMovieDetailsBinding.ivMoviePoster);

    }

}
