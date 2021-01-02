package com.example.popular.data

import com.example.core.network.responses.FilmResultResponse
import com.example.core.network.service.MovieService
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val networkService: MovieService,
) : PopularRepository {


    override suspend fun fetchPopularFilms(page: Int): Response<FilmResultResponse> =
        networkService.getPopularFilms(page)


    override suspend fun fetchTopRatedFilms(page: Int): Response<FilmResultResponse> =
        networkService.getTopRatedFilms(page)


}