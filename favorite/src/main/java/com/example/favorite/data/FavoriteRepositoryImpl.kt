package com.example.favorite.data

import com.example.core.db.dao.FilmDao
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    val dao: FilmDao,
) : FavoriteRepository {

    override fun getSavedFilm() =
        dao.getFilmAll()
}