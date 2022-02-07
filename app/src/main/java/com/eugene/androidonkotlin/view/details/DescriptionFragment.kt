package com.eugene.androidonkotlin.view.details

import android.os.Bundle
import androidx.fragment.app.*
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.eugene.androidonkotlin.databinding.DescriptionFragmentBinding
import com.eugene.androidonkotlin.model.MainMovie
import com.eugene.androidonkotlin.viewmodel.DescriptionViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DescriptionFragment : Fragment() {
    private var _binding: DescriptionFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DescriptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = DescriptionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getLong("movie id")
        viewModel.saveId(id!!)
        displayMovie()
        goBackToFragment()
        viewModel.getMovieFromServer()
    }

    private fun displayMovie() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.loadingProgress.collect { binding.descriptionLoadingLayout.alpha = it }
            }

            launch {
                viewModel.movieImage
                    .collect {
                        Glide.with(this@DescriptionFragment)
                            .load("https://image.tmdb.org/t/p/w500${it}")
                            .into(binding.descriptionImage)
                    }
            }

            launch {
                viewModel.movieTitle.collect { binding.descriptionTitle.text = it }
            }

            launch {
                viewModel.movieOverview.collect { binding.descriptionOverview.text = it }
            }

            launch {
                viewModel.errorCode.collect { Toast.makeText(context, it.idMessage, Toast.LENGTH_SHORT).show() }
            }
        }
    }

    private fun goBackToFragment() {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }


    private fun setMovie(mainMovie: MainMovie) {
//        saveMovie(movie)
//        with(binding) {
//            titleDescription.text = movie.title
//            Picasso.get().load("https:${movie.image}").into(imageViewDescription)
//            imageViewDescription.drawable
//            textViewAnyInformation.text = movie.rating
//            textViewDescription.text = movie.description
//        }
    }

    private fun saveMovie(mainMovie: MainMovie) {
//        viewModel.saveMovieToDB(movie)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(movieId: Long): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundleOf("movie id" to movieId)
            return fragment
        }
    }
}