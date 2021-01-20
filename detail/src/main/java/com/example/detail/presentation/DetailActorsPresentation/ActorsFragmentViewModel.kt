package com.example.detail.presentation.DetailActorsPresentation

import androidx.lifecycle.ViewModel
import com.example.detail.data.DetailRepository
import javax.inject.Inject

class ActorsFragmentViewModel @Inject constructor(
    val repository: DetailRepository,
) : ViewModel() {
}