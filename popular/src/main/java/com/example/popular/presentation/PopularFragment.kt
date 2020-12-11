package com.example.popular.presentation

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
import com.example.popular.R
import com.example.popular.di.DaggerPopularComponent
import com.example.popular.di.PopularComponent
import javax.inject.Inject


const val TAG = "PopularFragment"
class PopularFragment: Fragment(R.layout.fragment_popular) {



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PopularFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PopularComponent.injectFragment(this)

        if(viewModel != null){
            Log.d(TAG,"onAttach")
        }
    }
}