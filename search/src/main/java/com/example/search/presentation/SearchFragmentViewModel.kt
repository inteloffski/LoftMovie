package com.example.search.presentation

import androidx.lifecycle.ViewModel
import com.example.search.data.SearchRepository
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {
}