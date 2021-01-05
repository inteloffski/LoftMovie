package com.example.core.network.responses

import com.google.gson.annotations.SerializedName

data class FilmResultResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val items: List<Film>,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int
)