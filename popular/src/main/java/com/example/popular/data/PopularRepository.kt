package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.core.network.responses.FilmDTO.Film
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.utils.Resource
import retrofit2.Response


interface PopularRepository {

    suspend fun fetchPopularFilms(
        page: Int,
    ): Response<FilmResultResponse>

    suspend fun fetchTopRatedFilms(
        page: Int,
    ): Response<FilmResultResponse>


    fun getState(): LiveData<Resource<FilmResultResponse>>

    fun listIsEmpty(): Boolean

    fun getFilmList(): LiveData<PagedList<Film>>

    fun observeLocalPagedSets(): LiveData<PagedList<Film>>

    fun refresh()

}

