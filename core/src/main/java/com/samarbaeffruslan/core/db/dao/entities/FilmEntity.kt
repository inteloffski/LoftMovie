package com.samarbaeffruslan.core.db.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "filmEntity"
)
data class FilmEntity(
    val adult: Boolean?,
    val backdropPath: String?,
    //@field:SerializedName("genre_ids")
    //val genreIds: List<Int>,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    val popularity: Double?,
    var posterPath: String?,
    val releaseDate: String?,
    var title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    var isFavorite: Boolean = false
): Serializable