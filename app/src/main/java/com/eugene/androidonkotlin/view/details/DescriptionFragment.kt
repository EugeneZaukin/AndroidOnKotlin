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

private const val MAIN_LINK = "https://api.kinopoisk.cloud/movies/1143242/token/fe198beca21b5f01d844f2db52d2bb2f"

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
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Movie()

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieFromRemoteSource(MAIN_LINK)
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
        with(binding) {
            titleDescription.text = movie.title
            textViewAnyInformation.text = movie.rating
            textViewDescription.text = movie.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}