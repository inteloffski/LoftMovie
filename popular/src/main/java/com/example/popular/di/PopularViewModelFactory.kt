package com.example.popular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popular.presentation.PopularFragmentViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class PopularViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PopularFragmentViewModel::class.java)){
            return PopularFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}