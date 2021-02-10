package com.example.favorite.data

import com.example.core.db.dao.FilmDao
import com.example.core.db.dao.entities.FilmEntity
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    val dao: FilmDao,
) : FavoriteRepository {

    override fun getSavedFilm() =
        dao.getFilmAllisFavorite()

    override suspend fun deleteFilm(filmEntity: FilmEntity) {
        dao.deleteFilm(filmEntity)
    }

    override suspend fun upsertFilm(filmEntity: FilmEntity) {
        dao.insertFilm(filmEntity)
    }
}