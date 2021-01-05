package com.example.popular.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import com.example.core.network.service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FilmDataSourceFactory @Inject constructor(
    private val service: MovieService,
): DataSource.Factory<Int, Film>() {


    val liveData = MutableLiveData<FilmDataSource>()

    override fun create(): DataSource<Int, Film> {
        val filmDataSource = FilmDataSource(service)
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