package com.example.popular.data

import com.example.core.network.responses.FilmResultResponse
import com.example.core.network.service.MovieService
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val networkService: MovieService
): PopularRepository {
    override suspend fun fetchPopularFilms() = networkService.getPopularFilms()


    override suspend fun fetchTopRatedFilms() = networkService.getTopRatedFilms()


}