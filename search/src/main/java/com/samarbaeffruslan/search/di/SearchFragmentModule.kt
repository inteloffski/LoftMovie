package com.samarbaeffruslan.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.detail.data.DetailRepository
import com.samarbaeffruslan.detail.data.DetailRepositoryImpl
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.samarbaeffruslan.search.data.SearchRepository
import com.samarbaeffruslan.search.data.SearchRepositoryImpl
import com.samarbaeffruslan.search.presentation.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    fun bindViewModel(viewModel: SearchFragmentViewModel): ViewModel

    @Binds
    fun provideDetailRepository(repository: DetailRepositoryImpl): DetailRepository

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    fun bindDetailViewModel(viewModel: DetailFragmentViewModel): ViewModel


    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: SearchRepositoryImpl): SearchRepository
}