package com.example.favorite.presentation

import androidx.lifecycle.ViewModel
import com.example.favorite.data.FavoriteRepository
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {

    fun getSavedFilm() = repository.getSavedFilm()
}