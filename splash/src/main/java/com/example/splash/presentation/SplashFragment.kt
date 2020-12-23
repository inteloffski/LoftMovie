package com.example.splash.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.splash.R
import com.example.splash.di.DaggerSplashComponent
import com.example.splash.di.SplashComponent
import javax.inject.Inject


class SplashFragment : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SplashFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        SplashComponent.injectFragment(this)
        super.onAttach(context)

        if(viewModel != null){
            Log.d(TAG,"onAttach")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}