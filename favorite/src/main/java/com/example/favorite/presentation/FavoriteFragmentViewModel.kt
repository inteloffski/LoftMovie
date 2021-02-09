package com.example.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.db.dao.entities.FilmEntity
import com.example.favorite.data.FavoriteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {

    fun getSavedFilm() = repository.getSavedFilm()

    fun deleteFilm(filmEntity: FilmEntity) = viewModelScope.launch {
        repository.deleteFilm(filmEntity)
    }
}