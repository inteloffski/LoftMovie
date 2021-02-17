package com.example.search.data

import com.example.core.network.responses.FilmDTO.FilmResultResponse
import retrofit2.Response


interface SearchRepository {

    suspend fun searchFilm(query: String): Response<FilmResultResponse>

}