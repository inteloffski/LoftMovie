package com.example.favorite.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.FavoriteNavigator
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteComponent
import javax.inject.Inject


const val TAG = "FavoriteFragment"

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var ctx: Context

    @Inject
    lateinit var navigator: FavoriteNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        FavoriteComponent.injectFragment(this)
        super.onAttach(context)

        if (viewModel != null && navigator != null && ctx != null) {
            Log.d(TAG, "onAttach")
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}