package com.samarbaeffruslan.popular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.detail.data.DetailRepository
import com.samarbaeffruslan.detail.data.DetailRepositoryImpl
import com.samarbaeffruslan.popular.data.*
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.samarbaeffruslan.popular.presentation.PopularFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface PopularFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularFragmentViewModel::class)
    fun bindPopularViewModel(viewModel: PopularFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    fun bindDetailViewModel(viewModel: DetailFragmentViewModel): ViewModel


    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun provideDetailRepository(repository: DetailRepositoryImpl): DetailRepository











}