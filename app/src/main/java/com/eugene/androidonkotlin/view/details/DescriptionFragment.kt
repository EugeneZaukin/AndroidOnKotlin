package com.eugene.androidonkotlin.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eugene.androidonkotlin.databinding.FragmentDescriptionBinding
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.viewmodel.AppState
import com.eugene.androidonkotlin.viewmodel.DescriptionViewModel
import com.squareup.picasso.Picasso

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieBundle: Movie

    private val viewModel: DescriptionViewModel by lazy { ViewModelProvider(this).get(DescriptionViewModel::class.java) }

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(bundle: Bundle): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Movie()

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieFromRemoteSource()
    }

    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                setMovie(appState.movieSuccess[0])
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }
        }
    }

    private fun setMovie(movie: Movie) {
//        saveMovie(movie)
//        with(binding) {
//            titleDescription.text = movie.title
//            Picasso.get().load("https:${movie.image}").into(imageViewDescription)
//            imageViewDescription.drawable
//            textViewAnyInformation.text = movie.rating
//            textViewDescription.text = movie.description
//        }
    }

    private fun saveMovie(movie: Movie) {
        viewModel.saveMovieToDB(movie)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}