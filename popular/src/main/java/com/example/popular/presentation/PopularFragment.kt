package com.example.popular.presentation

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.filmList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        adapter = PopularFilmAdapter()
        recycler.apply {
            this.adapter = this@PopularFragment.adapter
            layoutManager = GridLayoutManager(activity, 3)
        }

    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        isLoading = true
    }

}