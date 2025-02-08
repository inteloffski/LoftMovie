package com.samarbaeffruslan.favorite.data

import androidx.lifecycle.LiveData
import com.samarbaeffruslan.core.db.dao.entities.FilmEntity

interface FavoriteRepository {

    fun getSavedFilm(): LiveData<List<FilmEntity>>

    suspend fun deleteFilm(filmEntity: FilmEntity)

    suspend fun upsertFilm(filmEntity: FilmEntity)
}