package com.samarbaeffruslan.search.data

import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmResultResponse
import retrofit2.Response


interface SearchRepository {

    suspend fun searchFilm(query: String): Response<FilmResultResponse>

}