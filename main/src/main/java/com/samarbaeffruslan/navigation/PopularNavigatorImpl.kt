package com.samarbaeffruslan.navigation

import androidx.navigation.NavController
import com.samarbaeffruslan.core.navigation.PopularNavigator
import com.samarbaeffruslan.main.R
import javax.inject.Inject

class PopularNavigatorImpl @Inject constructor(): PopularNavigator {

    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_popularFragment_to_detailFragment)
    }
}