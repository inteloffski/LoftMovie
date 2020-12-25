package com.example.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.data.MainRepository
import com.example.data.MainRepositoryImpl
import com.example.main.MainActivityViewModel
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