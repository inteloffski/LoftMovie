package com.example.popular.presentation

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.popular.R
import com.example.popular.di.PopularComponent
import javax.inject.Inject


const val TAG = "PopularFragment"
class PopularFragment: Fragment(R.layout.fragment_popular) {



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PopularFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        PopularComponent.injectFragment(this)
        super.onAttach(context)


        if(viewModel != null){
            Log.d(TAG,"onAttach")
        }
    }
}