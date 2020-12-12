package com.example.favorite.presentation

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.FavoriteNavigator
import com.example.favorite.R
import com.example.favorite.di.FavoriteComponent
import javax.inject.Inject


const val TAG = "FavoriteFragment"

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

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

}