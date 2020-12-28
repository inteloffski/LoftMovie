package com.example.popular.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        viewModel.popularFilm.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        adapter.differ.submitList(it.items)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }

        })
    }

    private fun setupRecyclerView() {
        adapter = PopularFilmAdapter()
        recycler.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
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