package com.example.search.data

import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.network.service.MovieService
import retrofit2.Response
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val service: MovieService
): SearchRepository {


    override suspend fun searchFilm(query: String): Response<FilmResultResponse> =
        service.searchFilm(query = query)



}