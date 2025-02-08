package com.samarbaeffruslan.popular.data


import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.network.service.MovieService
import io.reactivex.Observable
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val service: MovieService
) : PopularRepository {

    override fun fetchPopularFilms(): Observable<PagingData<FilmDTO>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FilmDataSource(service) }
        ).observable
    }
}