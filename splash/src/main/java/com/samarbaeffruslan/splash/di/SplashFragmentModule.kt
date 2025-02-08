package com.samarbaeffruslan.splash.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.splash.data.SplashRepository
import com.samarbaeffruslan.splash.data.SplashRepositoryImpl
import com.samarbaeffruslan.splash.presentation.SplashFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface SplashFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashFragmentViewModel::class)
    fun bindViewModel(viewModel: SplashFragmentViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: SplashRepositoryImpl): SplashRepository
}
