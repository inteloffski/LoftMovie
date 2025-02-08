package com.samarbaeffruslan.main

import androidx.lifecycle.ViewModel
import com.samarbaeffruslan.data.MainRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {


}