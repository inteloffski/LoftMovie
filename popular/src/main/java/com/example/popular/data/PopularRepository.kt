package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.core.db.dao.entities.FilmEntity
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.utils.Resource
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response


interface PopularRepository {

    fun fetchPopularFilms(): Observable<PagingData<FilmDTO>>

    suspend fun fetchTopRatedFilms(
        page: Int,
    ): Response<FilmResultResponse>

    fun refresh()

}

