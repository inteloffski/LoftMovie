package com.samarbaeffruslan.core_api.providers

import com.samarbaeffruslan.core.navigation.FavoriteNavigator
import com.samarbaeffruslan.core.navigation.PopularNavigator
import com.samarbaeffruslan.core.navigation.SearchNavigator
import com.samarbaeffruslan.core.navigation.SplashNavigator

interface NavigatorProvider {

    fun provideFavoriteNavigator(): FavoriteNavigator
    fun providePopularNavigator(): PopularNavigator
    fun provideSearchNavigator(): SearchNavigator
    fun provideSplashNavigator(): SplashNavigator
}