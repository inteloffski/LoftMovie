package com.example.popular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.popular.data.PopularRepository
import com.example.popular.data.PopularRepositoryImpl
import com.example.popular.presentation.PopularFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PopularFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularFragmentViewModel::class)
    fun bindViewModel(viewModel: PopularFragmentViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: PopularRepositoryImpl): PopularRepository

}