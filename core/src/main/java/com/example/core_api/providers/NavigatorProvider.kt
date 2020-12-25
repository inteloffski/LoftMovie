package com.example.core_api.providers

import com.example.core.navigation.FavoriteNavigator
import com.example.core.navigation.PopularNavigator
import com.example.core.navigation.SearchNavigator
import com.example.core.navigation.SplashNavigator

interface NavigatorProvider {

    fun provideFavoriteNavigator(): FavoriteNavigator
    fun providePopularNavigator(): PopularNavigator
    fun provideSearchNavigator(): SearchNavigator
    fun provideSplashNavigator(): SplashNavigator
}