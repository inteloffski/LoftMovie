package com.example.search.presentation

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.search.R
import com.example.search.di.SearchComponent
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