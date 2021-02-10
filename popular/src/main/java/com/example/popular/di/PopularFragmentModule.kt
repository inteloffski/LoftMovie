package com.example.popular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.detail.data.DetailRepository
import com.example.detail.data.DetailRepositoryImpl
import com.example.popular.data.*
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.popular.presentation.PopularFragmentViewModel
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