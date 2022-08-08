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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel> {
        requireContext().appComponent.viewModelFactory()
    }
    private var movieAdapter: MovieAdapter? = MovieAdapter() { viewModel.goToDescriptionScreen(it) }

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
        initFlows()
        addSwipeToRefresh()
    }

    private fun initRecyclerView() {
        with(binding.recyclerViewMovies) {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            with(viewModel) {
                launch {
                    loadingProgress.collect { binding.swipeToRefresh.isRefreshing = it }
                }

                launch { moviesPagedList.collectLatest { movieAdapter?.submitData(it) } }

                launch {
                    errorCode.collect {
                        Toast.makeText(context, it.idMessage, Toast.LENGTH_SHORT).show()
                    }
                }

                launch { switchDescriptionFragment.collect { addTransaction(it) } }
            }
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
            checkNotNull(movieAdapter).refresh()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        movieAdapter = null
    }
}

