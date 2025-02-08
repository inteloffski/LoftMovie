package com.samarbaeffruslan.navigation

import androidx.navigation.NavController
import com.samarbaeffruslan.core.navigation.FavoriteNavigator
import com.samarbaeffruslan.main.R
import javax.inject.Inject

class FavoriteNavigatorImpl @Inject constructor(): FavoriteNavigator {
    override fun navigateToDetail(navController: NavController) {
        navController.navigate(R.id.action_favoriteFragment_to_detailFragment)

    }
}