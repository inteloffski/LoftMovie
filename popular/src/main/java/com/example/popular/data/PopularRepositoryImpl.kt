package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
import com.example.core.network.service.MovieService
import com.example.popular.utils.Resource
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val service: MovieService,
) : PopularRepository {


    override suspend fun fetchPopularFilms(page: Int): Response<FilmResultResponse> =
        service.getPopularFilms(page)


    override suspend fun fetchTopRatedFilms(page: Int): Response<FilmResultResponse> =
        service.getTopRatedFilms(page)


}