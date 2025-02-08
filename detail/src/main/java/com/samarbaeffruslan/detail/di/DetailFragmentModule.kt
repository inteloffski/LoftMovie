package com.samarbaeffruslan.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.detail.data.DetailRepository
import com.samarbaeffruslan.detail.data.DetailRepositoryImpl
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    fun bindViewModel(viewModel: DetailFragmentViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: DetailRepositoryImpl): DetailRepository
}