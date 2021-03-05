package com.eugene.androidonkotlin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.eugene.androidonkotlin.viewmodel.MainViewModel
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.MainFragmentBinding
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun  newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getMovieFromLocal()    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                val movieSuccess = appState.movieSuccess
                binding.loadingLayout.visibility = View.GONE
                setData(movieSuccess)
            }

            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.itemCard, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") {viewModel.getMovieFromLocal()}
                        .show()
            }
        }
    }

    private fun setData(movieAppState: Movie) {
        binding.itemTitle.text = movieAppState.title
        binding.itemImage.setImageResource(movieAppState.image)
        binding.itemRating.text = movieAppState.rating
    }
}