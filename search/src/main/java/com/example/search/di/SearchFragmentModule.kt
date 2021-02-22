package com.example.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.detail.data.DetailRepository
import com.example.detail.data.DetailRepositoryImpl
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.search.data.SearchRepository
import com.example.search.data.SearchRepositoryImpl
import com.example.search.presentation.SearchFragmentViewModel
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