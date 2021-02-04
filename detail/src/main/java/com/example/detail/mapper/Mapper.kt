package com.example.detail.mapper

import com.example.core.db.dao.entities.FilmEntity
import com.example.core.db.dao.mapper.BaseMapper
import com.example.core.network.responses.FilmDTO.FilmDTO

class Mapper : BaseMapper<FilmDTO, FilmEntity>() {

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
            voteCount = model.voteCount
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
