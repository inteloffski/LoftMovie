package com.example.popular.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FilmDataSourceFactory @Inject constructor(
    private val repository: PopularRepository
): DataSource.Factory<Int, Film>() {


    private val liveData = MutableLiveData<FilmDataSource>()

    override fun create(): DataSource<Int, Film> {

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