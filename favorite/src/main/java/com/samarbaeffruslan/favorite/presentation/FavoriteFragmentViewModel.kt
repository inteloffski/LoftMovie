package com.samarbaeffruslan.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarbaeffruslan.core.db.dao.entities.FilmEntity
import com.samarbaeffruslan.favorite.data.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
    private val repository: FavoriteRepository,
) : ViewModel() {

    fun getSavedFilm() = repository.getSavedFilm()

    fun deleteFilm(filmEntity: FilmEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFilm(filmEntity)
    }

    fun saveFilm(filmEntity: FilmEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.upsertFilm(filmEntity)
    }


}