package com.samarbaeffruslan.core.db.dao.mapper

import com.samarbaeffruslan.core.db.dao.entities.FilmEntity
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import javax.inject.Inject

class FilmDTOFilmEntityMapper @Inject constructor() : BaseMapper<FilmDTO, FilmEntity>() {

    override fun map(model: FilmDTO): FilmEntity {
        val entityList = FilmEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = model.backdropPath,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            isFavorite = false
        )
        return entityList
    }

    override fun reverseMap(entity: FilmEntity): FilmDTO {
        val modelList = FilmDTO(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
        return modelList
    }
}
