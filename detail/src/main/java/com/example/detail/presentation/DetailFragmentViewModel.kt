package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import com.example.detail.data.DetailRepository
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(
    private val repository: DetailRepository): ViewModel() {
}