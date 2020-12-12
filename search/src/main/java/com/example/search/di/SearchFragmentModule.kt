package com.example.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
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
    fun viewModelFactory(factory: SearchViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun providePopularRepository(repository: SearchRepositoryImpl): SearchRepository
}