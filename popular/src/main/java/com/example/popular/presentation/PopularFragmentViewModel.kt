package com.example.popular.presentation

import androidx.lifecycle.ViewModel
import com.example.popular.data.PopularRepository
import javax.inject.Inject

class PopularFragmentViewModel @Inject constructor(
    private val repository: PopularRepository
): ViewModel() {
}