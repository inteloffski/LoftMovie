package com.example.popular.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.network.responses.Film
import com.example.popular.R
import com.example.popular.adapters.PopularFilmAdapter
import com.example.popular.di.PopularComponent
import com.example.popular.utils.Resource
import kotlinx.android.synthetic.main.fragment_popular.*
import javax.inject.Inject


class PopularFragment: Fragment(R.layout.fragment_popular) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PopularFragmentViewModel> { viewModelFactory }

    lateinit var adapter: PopularFilmAdapter

    var isLoading = false

    override fun onAttach(context: Context) {
        PopularComponent.injectFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()




        activity?.let {
            if (viewModel.isNetworkAvailable(it)){
                initPagingList()
                initState()
            } else {
                readFilmDatabase()
            }


        }


    }

    private fun setupRecyclerView() {
        adapter = PopularFilmAdapter()
        recycler.apply {
            this.adapter = this@PopularFragment.adapter
            layoutManager = GridLayoutManager(activity, 3)
        }

    }

    private fun initState() {
        viewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()

                }
                is Resource.Error -> {
                    hideProgressBar()

                }
            }

        })
    }

    private fun initPagingList(){
        viewModel.getFilmList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun readFilmDatabase(){
        viewModel.observeLocalPagedSets().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        if(viewModel.getListIsEmpty()) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
        isLoading = true
    }

}