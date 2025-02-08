package com.samarbaeffruslan.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samarbaeffruslan.core.di.viewmodel.ViewModelKey
import com.samarbaeffruslan.core.viewmodelfactory.ViewModelFactory
import com.samarbaeffruslan.detail.data.DetailRepository
import com.samarbaeffruslan.detail.data.DetailRepositoryImpl
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.samarbaeffruslan.favorite.data.FavoriteRepository
import com.samarbaeffruslan.favorite.data.FavoriteRepositoryImpl
import com.samarbaeffruslan.favorite.presentation.FavoriteFragmentViewModel
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