package com.example.favorite.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.core.di.component.DaggerCoreComponent
import com.example.favorite.R
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.favorite.di.FavoriteComponent
import com.example.favorite.di.FavoriteViewModelFactory
import javax.inject.Inject
import kotlin.math.log


const val TAG = "FavoriteFragment"

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var ctx: Context

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FavoriteComponent.injectFragment(this)

        if (viewModel != null && ctx != null) {
            Log.d(TAG, "onAttach")
        }
    }

}