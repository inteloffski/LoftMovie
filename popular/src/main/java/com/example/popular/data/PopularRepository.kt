package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.core.db.dao.entities.FilmEntity
import com.example.core.network.responses.FilmDTO.FilmDTO
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

    fun getFilmList(): LiveData<PagedList<FilmDTO>>

    fun observeLocalPagedSets(): LiveData<PagedList<FilmEntity>>

    fun refresh()

}

