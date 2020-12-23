package com.example.splash.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.core.navigation.SplashNavigator
import com.example.splash.R
import com.example.splash.di.DaggerSplashComponent
import com.example.splash.di.SplashComponent
import javax.inject.Inject


class SplashFragment : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var navigator: SplashNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SplashFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        SplashComponent.injectFragment(this)
        super.onAttach(context)

        if(viewModel != null){

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler()
    }

    private fun handler(){
        val handler = Handler()
        val navController = findNavController()
        handler.postDelayed({
            navigator.navigateToPopular(navController)

        }, 3000)
    }


}