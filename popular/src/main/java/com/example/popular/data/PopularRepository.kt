package com.example.popular.data

import androidx.paging.PagingData
import com.example.core.network.responses.FilmDTO.FilmDTO
import io.reactivex.Observable


interface PopularRepository {

    fun fetchPopularFilms(): Observable<PagingData<FilmDTO>>
}

