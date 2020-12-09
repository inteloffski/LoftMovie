package com.example.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.search.presentation.SearchFragmentViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchFragmentViewModel::class.java)){
            return SearchFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}