package com.example.detail.presentation.DetailDescriptionPresentation

import androidx.lifecycle.ViewModel
import com.example.detail.data.DetailRepository
import javax.inject.Inject

class DescriptionFragmentViewModel @Inject constructor(
    val repository: DetailRepository
): ViewModel() {
}