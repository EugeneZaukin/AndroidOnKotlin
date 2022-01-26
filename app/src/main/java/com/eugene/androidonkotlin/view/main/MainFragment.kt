package com.eugene.androidonkotlin.view.main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.MainFragmentBinding
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.view.addToBackStack
import com.eugene.androidonkotlin.view.details.DescriptionFragment
import com.eugene.androidonkotlin.viewmodel.AppState
import com.eugene.androidonkotlin.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    //Реализация extension-функции
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
            val bundle = Bundle().apply { putParcelable(DescriptionFragment.BUNDLE_EXTRA, movie) }
            activity?.supportFragmentManager?.addToBackStack(DescriptionFragment::class, bundle)
        }
    })


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getMovieFromLocal()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(appState.movieSuccess)
            }

            is AppState.Error -> {
                var itemCard = view?.findViewById<CardView>(R.id.item_card)


                binding.loadingLayout.visibility = View.GONE
                if (itemCard != null) {
                    Snackbar.make(itemCard, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") { viewModel.getMovieFromLocal() }
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }
}


