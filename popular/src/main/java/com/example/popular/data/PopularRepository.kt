package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
import com.example.popular.utils.Resource
import retrofit2.Response


interface PopularRepository {

    var filmList: LiveData<PagedList<Film>>

    suspend fun fetchPopularFilms(
        page: Int,
    ): Response<FilmResultResponse>

    suspend fun fetchTopRatedFilms(
        page: Int,
    ): Response<FilmResultResponse>

    fun getState(): LiveData<Resource<FilmResultResponse>>

    fun listIsEmpty(): Boolean

}

