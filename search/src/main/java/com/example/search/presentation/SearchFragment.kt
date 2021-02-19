package com.example.search.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.navigation.SearchNavigator
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.search.R
import com.example.search.adapters.SearchAdapter
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.di.SearchComponent
import com.example.search.utils.SearchResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.Listener {

    private var adapter = SearchAdapter(this)

    @Inject
    lateinit var navigator: SearchNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by activityViewModels<SearchFragmentViewModel> { viewModelFactory }
    private val detailViewModel by activityViewModels<DetailFragmentViewModel>() { viewModelFactory }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        SearchComponent.injectFragment(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.searchResultLiveData.observe(viewLifecycleOwner, Observer {
            handleSearchResult(it)
        })

        binding.searchRepo.doAfterTextChanged {
            lifecycleScope.launch {
                viewModel.queryChannel.send(it.toString())
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleSearchResult(it: SearchResult) {
        when (it) {
            is SearchResult.LoadingResult -> {
                binding.progress.visibility = View.VISIBLE
            }

            is SearchResult.SuccessResult -> {
                binding.progress.visibility = View.INVISIBLE
                adapter.submitList(it.result)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter(this)
        binding.recycler.apply {
            this.adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(activity)
        }


    }

    override fun onMovieClicked(filmDTO: FilmDTO) {
        val navController = findNavController()
        detailViewModel.selectedMovie(filmDTO)
        navigator.navigateToDetail(navController)

    }


}