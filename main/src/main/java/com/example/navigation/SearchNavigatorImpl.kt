package com.example.navigation

import androidx.navigation.NavController
import com.example.core.navigation.SearchNavigator
import com.example.main.R
import javax.inject.Inject

class SearchNavigatorImpl @Inject constructor(): SearchNavigator {

    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_searchFragment_to_detailFragment)
    }
}