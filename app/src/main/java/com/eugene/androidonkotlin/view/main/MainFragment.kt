package com.eugene.androidonkotlin.view.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.MainFragmentBinding
import com.eugene.androidonkotlin.view.details.DescriptionFragment
import com.eugene.androidonkotlin.viewmodel.MainViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
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
        openDescriptionMovie()
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
            launch {
                viewModel.loadingProgress.collect { binding.loadingLayout.alpha = it }
            }

            launch {
                viewModel.moviesList
                    .collect { FastAdapterDiffUtil[itemAdapter] = it.map { movie -> MovieItem(movie) } }
            }

            launch {
                viewModel.errorCode
                    .collect { Toast.makeText(context, it.idMessage, Toast.LENGTH_SHORT).show() }
            }
        }
    }

    private fun openDescriptionMovie() {
        fastAdapter.onClickListener = { _, _, _, position ->
            viewModel.goToDescriptionScreen(position)
            false
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.switchDescriptionFragment
                .collect { addTransaction(it) }
        }
    }

    private fun addTransaction(movieId: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, DescriptionFragment.newInstance(movieId))
            .addToBackStack("main")
            .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

