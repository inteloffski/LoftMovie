package com.samarbaeffruslan.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.data.MainRepository
import com.samarbaeffruslan.data.MainRepositoryImpl
import com.samarbaeffruslan.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun provideUserRepository(repository: MainRepositoryImpl): MainRepository

}