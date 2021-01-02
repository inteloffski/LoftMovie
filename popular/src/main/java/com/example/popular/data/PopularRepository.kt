package com.example.popular.data

import com.example.core.network.responses.FilmResultResponse
import retrofit2.Response


interface PopularRepository {

    suspend fun fetchPopularFilms(
        page: Int
    ): Response<FilmResultResponse>

    suspend fun fetchTopRatedFilms(
        page: Int
    ): Response<FilmResultResponse>
}

