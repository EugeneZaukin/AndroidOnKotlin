package com.eugene.androidonkotlin.view.main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
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
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private lateinit var itemAdapter: ItemAdapter<MovieItem>
    private lateinit var fastAdapter: FastAdapter<MovieItem>

    //Реализация extension-функции
//    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
//        override fun onItemViewClick(movie: Movie) {
//            val bundle = Bundle().apply { putParcelable(DescriptionFragment.BUNDLE_EXTRA, movie) }
//            activity?.supportFragmentManager?.addToBackStack(DescriptionFragment::class, bundle)
//        }
//    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        itemAdapter.add(listOf(MovieItem(), MovieItem(), MovieItem()))

        viewModel.getMoviesFromServer()
    }


    private fun initRecyclerView() {
        binding.recyclerViewMovies.apply {
            setHasFixedSize(true)
            itemAnimator = null
            itemAdapter = ItemAdapter()
            fastAdapter = FastAdapter.Companion.with(itemAdapter)
            adapter = fastAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}


