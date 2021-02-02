package com.example.favorite.data

import androidx.lifecycle.LiveData
import com.example.core.db.dao.FilmDao
import com.example.core.network.responses.FilmDTO.Film
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    val dao: FilmDao,
) : FavoriteRepository {

    override fun getSavedFilm() =
        dao.getFilmAll()
}