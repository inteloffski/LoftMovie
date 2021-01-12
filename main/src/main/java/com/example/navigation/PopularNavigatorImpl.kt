package com.example.navigation

import androidx.navigation.NavController
import com.example.core.navigation.PopularNavigator
import com.example.main.R
import javax.inject.Inject

class PopularNavigatorImpl @Inject constructor(): PopularNavigator {

    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_popularFragment_to_detailFragment)
    }
}