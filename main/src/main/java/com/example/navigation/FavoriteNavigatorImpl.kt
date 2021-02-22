package com.example.navigation

import androidx.navigation.NavController
import com.example.core.navigation.FavoriteNavigator
import com.example.main.R
import javax.inject.Inject

class FavoriteNavigatorImpl @Inject constructor(): FavoriteNavigator {
    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_favoriteFragment_to_detailFragment)

    }
}