package com.example.popular.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigation.PopularNavigator
import com.example.core.network.responses.Film
import com.example.popular.R
import com.example.popular.adapters.PopularFilmAdapter
import com.example.popular.di.PopularComponent
import com.example.popular.utils.Resource
import kotlinx.android.synthetic.main.fragment_popular.*
import javax.inject.Inject


class PopularFragment : Fragment(R.layout.fragment_popular), PopularFilmAdapter.Listener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PopularFragmentViewModel> { viewModelFactory }

    lateinit var adapter: PopularFilmAdapter

    @Inject
    lateinit var navigator: PopularNavigator

    var isLoading = false

    override fun onAttach(context: Context) {
        PopularComponent.injectFragment(this)
        super.onAttach(context)
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
        recycler.apply {
            this.adapter = this@PopularFragment.adapter
            layoutManager = GridLayoutManager(activity, 3)
        }


    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun readFilmDatabase() {
        viewModel.observeLocalPagedSets().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        if (viewModel.getListIsEmpty()) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
        isLoading = true
    }

    private fun showToast() {
        Toast.makeText(activity, "Check internet", Toast.LENGTH_SHORT).show()
    }

    private fun swipeToRefresh() {
        swipe.setOnRefreshListener {
            activity?.let { activity ->
                if (viewModel.isNetworkAvailable(activity)) {
                    viewModel.refresh()
                    viewModel.getFilmList().observe(viewLifecycleOwner, Observer { response ->
                        initState()
                        adapter.notifyDataSetChanged()
                        adapter.submitList(response)
                        swipe.isRefreshing = false
                    })
                } else {
                    swipe.isRefreshing = false
                    showToast()
                }
            }
        }
    }

    override fun onMovieClicked(film: Film) {
        val navController = findNavController()
        val bundle = bundleOf(
            "titleFilm" to film.title
        )
        navigator.navigateToDetail(navController, bundle)
        
    }


}