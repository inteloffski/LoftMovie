package com.example.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.viewmodelfactory.ViewModelFactory
import com.example.detail.data.DetailRepository
import com.example.detail.data.DetailRepositoryImpl
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.favorite.data.FavoriteRepository
import com.example.favorite.data.FavoriteRepositoryImpl
import com.example.favorite.presentation.FavoriteFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoriteFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFragmentViewModel::class)
    fun bindViewModel(viewModel: FavoriteFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    fun bindDetailViewModel(viewModel: DetailFragmentViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun provideFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    fun provideDetailRepository(repository: DetailRepositoryImpl): DetailRepository

}