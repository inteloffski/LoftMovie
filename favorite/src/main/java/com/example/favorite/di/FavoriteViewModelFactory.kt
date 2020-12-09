package com.example.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favorite.presentation.FavoriteFragmentViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteFragmentViewModel::class.java)){
            return FavoriteFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}