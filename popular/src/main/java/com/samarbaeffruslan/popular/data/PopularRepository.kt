package com.samarbaeffruslan.popular.data

import androidx.paging.PagingData
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import io.reactivex.Observable


interface PopularRepository {

    fun fetchPopularFilms(): Observable<PagingData<FilmDTO>>
}

