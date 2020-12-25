package com.example.navigation

import androidx.navigation.NavController
import com.example.core.navigation.SplashNavigator
import com.example.main.R
import javax.inject.Inject

class SplashNavigatorImpl @Inject constructor(): SplashNavigator {

    override fun navigateToPopular(navController: NavController) {
        navController.navigate(R.id.action_splashFragment_to_popularFragment)
    }
}