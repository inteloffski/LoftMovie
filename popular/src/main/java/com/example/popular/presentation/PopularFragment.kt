package com.example.popular.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigation.PopularNavigator
import com.example.core.network.responses.FilmDTO.Film
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.popular.R
import com.example.popular.adapters.PopularFilmAdapter
import com.example.popular.databinding.FragmentPopularBinding
import com.example.popular.di.PopularComponent
import com.example.popular.utils.Resource
import javax.inject.Inject


class PopularFragment : Fragment(R.layout.fragment_popular), PopularFilmAdapter.Listener {


    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PopularFragmentViewModel> { viewModelFactory }

    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }

    lateinit var adapter: PopularFilmAdapter

    @Inject
    lateinit var navigator: PopularNavigator

    var isLoading = false

    override fun onAttach(context: Context) {
        PopularComponent.injectFragment(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        activity?.let {
            if (viewModel.isNetworkAvailable(it)) {
                initPagingList()
                initState()
            } else {
                readFilmDatabase()
                showToast()
            }


        }
        swipeToRefresh()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initState() {
        viewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()

                }
                is Resource.Error -> {
                    hideProgressBar()
                    showToast()

                }
            }

        })
    }

    private fun initPagingList() {
        viewModel.getFilmList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        adapter = PopularFilmAdapter(this)
        binding.recycler.apply {
            this.adapter = this@PopularFragment.adapter
            layoutManager = GridLayoutManager(activity, 3)
        }


    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun readFilmDatabase() {
        viewModel.observeLocalPagedSets().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        if (viewModel.getListIsEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
        isLoading = true
    }

    private fun showToast() {
        Toast.makeText(activity, "Check internet", Toast.LENGTH_SHORT).show()
    }

    private fun swipeToRefresh() {
        binding.swipe.setOnRefreshListener {
            activity?.let { activity ->
                if (viewModel.isNetworkAvailable(activity)) {
                    viewModel.refresh()
                    viewModel.getFilmList().observe(viewLifecycleOwner, Observer { response ->
                        initState()
                        adapter.notifyDataSetChanged()
                        adapter.submitList(response)
                        binding.swipe.isRefreshing = false
                    })
                } else {
                    binding.swipe.isRefreshing = false
                    showToast()
                }
            }
        }
    }

    override fun onMovieClicked(film: Film) {
        val navController = findNavController()
        detailViewModel.selectedMovie(film)
        navigator.navigateToDetail(navController)
        
    }


}