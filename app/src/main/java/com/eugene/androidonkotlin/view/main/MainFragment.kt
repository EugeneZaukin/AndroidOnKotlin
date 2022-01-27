package com.eugene.androidonkotlin.view.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.eugene.androidonkotlin.databinding.MainFragmentBinding
import com.eugene.androidonkotlin.viewmodel.MainViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private lateinit var itemAdapter: ItemAdapter<MovieItem>
    private lateinit var fastAdapter: FastAdapter<MovieItem>

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
        displayMovies()
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

    private fun displayMovies() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.moviesList
                .collect {
                    FastAdapterDiffUtil[itemAdapter] = it.map { movie -> MovieItem(movie) }
                }
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


