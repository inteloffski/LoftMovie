package com.example.splash.presentation

import androidx.lifecycle.ViewModel
import com.example.splash.data.SplashRepository
import javax.inject.Inject

class SplashFragmentViewModel @Inject constructor(
    private val repository: SplashRepository
): ViewModel(){}