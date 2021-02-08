package com.example.popular.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.core.db.dao.FilmDao
import com.example.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.service.MovieService
import javax.inject.Inject

class FilmDataSourceFactory @Inject constructor(
    private val service: MovieService,
    private val dao: FilmDao,
    private val mapper: FilmDTOFilmEntityMapper
): DataSource.Factory<Int, FilmDTO>() {


    val liveData = MutableLiveData<FilmDataSource>()

    override fun create(): DataSource<Int, FilmDTO> {
        val filmDataSource = FilmDataSource(service, dao, mapper)
        liveData.postValue(filmDataSource)
        return  filmDataSource
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}