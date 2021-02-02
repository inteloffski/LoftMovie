package com.example.favorite.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.navigation.FavoriteNavigator
import com.example.favorite.R
import com.example.favorite.adapters.FavoriteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteComponent
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FavoriteAdapter

    @Inject
    lateinit var navigator: FavoriteNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        FavoriteComponent.injectFragment(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.getSavedFilm().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        adapter = FavoriteAdapter()
        binding.recyclerFavorite.apply {
            this.adapter = this@FavoriteFragment.adapter
            layoutManager = LinearLayoutManager(activity)
        }


    }


}