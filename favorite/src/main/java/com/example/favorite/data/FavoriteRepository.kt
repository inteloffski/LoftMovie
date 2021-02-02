package com.example.favorite.data

import androidx.lifecycle.LiveData
import com.example.core.network.responses.FilmDTO.Film

interface FavoriteRepository {

    fun getSavedFilm(): LiveData<List<Film>>
}