package com.samarbaeffruslan.navigation

import androidx.navigation.NavController
import com.samarbaeffruslan.core.navigation.SearchNavigator
import com.samarbaeffruslan.main.R
import javax.inject.Inject

class SearchNavigatorImpl @Inject constructor(): SearchNavigator {

    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_searchFragment_to_detailFragment)
    }
}