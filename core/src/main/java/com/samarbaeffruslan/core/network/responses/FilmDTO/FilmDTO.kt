package com.samarbaeffruslan.core.network.responses.FilmDTO

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmDTO(
    @field:SerializedName("adult")
    val adult: Boolean?,
    @field:SerializedName("backdrop_path")
    val backdropPath: String?,
    //@field:SerializedName("genre_ids")
    //val genreIds: List<Int>,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("original_language")
    val originalLanguage: String?,
    @field:SerializedName("original_title")
    var originalTitle: String?,
    @field:SerializedName("overview")
    var overview: String?,
    @field:SerializedName("popularity")
    val popularity: Double?,
    @field:SerializedName("poster_path")
    var posterPath: String?,
    @field:SerializedName("release_date")
    val releaseDate: String?,
    @field:SerializedName("title")
    var title: String?,
    @field:SerializedName("video")
    val video: Boolean?,
    @field:SerializedName("vote_average")
    val voteAverage: Double?,
    @field:SerializedName("vote_count")
    val voteCount: Int?
): Serializable