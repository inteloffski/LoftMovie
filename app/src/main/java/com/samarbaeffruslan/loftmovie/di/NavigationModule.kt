package com.samarbaeffruslan.loftmovie.di

import com.samarbaeffruslan.core.navigation.FavoriteNavigator
import com.samarbaeffruslan.core.navigation.PopularNavigator
import com.samarbaeffruslan.core.navigation.SearchNavigator
import com.samarbaeffruslan.core.navigation.SplashNavigator
import com.samarbaeffruslan.navigation.FavoriteNavigatorImpl
import com.samarbaeffruslan.navigation.PopularNavigatorImpl
import com.samarbaeffruslan.navigation.SearchNavigatorImpl
import com.samarbaeffruslan.navigation.SplashNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface NavigationModule {

    @Reusable
    @Binds
    fun favoriteNavigator(navigator: FavoriteNavigatorImpl): FavoriteNavigator

    @Reusable
    @Binds
    fun popularNavigator(navigator: PopularNavigatorImpl): PopularNavigator

    @Reusable
    @Binds
    fun searchNavigator(navigator: SearchNavigatorImpl): SearchNavigator

    @Reusable
    @Binds
    fun splashNavigator(navigator: SplashNavigatorImpl): SplashNavigator





}