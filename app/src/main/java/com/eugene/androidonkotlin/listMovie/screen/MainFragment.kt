package com.eugene.androidonkotlin.listMovie.screen

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.common.appComponent
import com.eugene.androidonkotlin.databinding.MainFragmentBinding
import com.eugene.androidonkotlin.movieDescription.screen.DescriptionFragment
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemAdapter<MovieItem>
    private lateinit var fastAdapter: FastAdapter<MovieItem>
    private val viewModel by viewModels<MainViewModel> {
        requireContext().appComponent.viewModelFactory()
    }

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
        addSwipeToRefresh()
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
                viewModel.loadingProgress.collect { binding.swipeToRefresh.isRefreshing = it }
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
        findNavController().navigate(
            R.id.action_mainFragment_to_descriptionFragment,
            bundleOf(DescriptionFragment.KEY_ID to movieId)
        )
    }

    private fun addSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getMoviesFromServer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

