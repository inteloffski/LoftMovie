package com.samarbaeffruslan.splash.presentation

import androidx.lifecycle.ViewModel
import com.samarbaeffruslan.splash.data.SplashRepository
import javax.inject.Inject

class SplashFragmentViewModel @Inject constructor(
    private val repository: SplashRepository
): ViewModel(){}