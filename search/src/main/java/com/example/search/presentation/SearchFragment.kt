package com.example.search.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.component.DaggerCoreComponent
import com.example.search.R
import com.example.search.di.DaggerSearchComponent
import com.example.search.di.SearchComponent
import com.example.search.di.SearchViewModelFactory
import kotlinx.android.synthetic.main.fragment_search.view.*
import javax.inject.Inject

const val TAG = "SearchFragment"
class SearchFragment: Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SearchFragmentViewModel> { viewModelFactory }


    override fun onAttach(context: Context) {
        SearchComponent.injectFragment(this)
        super.onAttach(context)


        if(viewModel != null){
            Log.d(TAG,"onAttach")
        }

    }
}