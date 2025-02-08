package com.samarbaeffruslan.search.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samarbaeffruslan.core.navigation.SearchNavigator
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.samarbaeffruslan.search.R
import com.samarbaeffruslan.search.adapters.SearchAdapter
import com.samarbaeffruslan.search.databinding.FragmentSearchBinding
import com.samarbaeffruslan.search.di.SearchComponent
import com.samarbaeffruslan.search.utils.SearchResult
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

    private val viewBinding: FragmentSearchBinding by viewBinding()


    override fun onAttach(context: Context) {
        SearchComponent.injectFragment(this)
        super.onAttach(context)

    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.searchResultLiveData.observe(viewLifecycleOwner, Observer {
            handleSearchResult(it)
        })

        viewBinding.searchRepo.doAfterTextChanged {
            lifecycleScope.launch {
                viewModel.queryChannel.send(it.toString())
            }
        }

    }




    private fun handleSearchResult(it: SearchResult) {
        when (it) {
            is SearchResult.LoadingResult -> {
                viewBinding.progress.visibility = View.VISIBLE
            }

            is SearchResult.SuccessResult -> {
                viewBinding.progress.visibility = View.INVISIBLE
                adapter.submitList(it.result)
            }

            SearchResult.EmptyQuery -> TODO()
            SearchResult.EmptyResult -> TODO()
            is SearchResult.ErrorResult -> TODO()
            SearchResult.TerminalError -> TODO()
        }
    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter(this)
        viewBinding.recycler.apply {
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