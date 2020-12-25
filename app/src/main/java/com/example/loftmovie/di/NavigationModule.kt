package com.example.loftmovie.di

import com.example.core.navigation.FavoriteNavigator
import com.example.core.navigation.PopularNavigator
import com.example.core.navigation.SearchNavigator
import com.example.core.navigation.SplashNavigator
import com.example.navigation.FavoriteNavigatorImpl
import com.example.navigation.PopularNavigatorImpl
import com.example.navigation.SearchNavigatorImpl
import com.example.navigation.SplashNavigatorImpl
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