package com.example.splash.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.splash.data.SplashRepository
import com.example.splash.data.SplashRepositoryImpl
import com.example.splash.presentation.SplashFragmentViewModel
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
