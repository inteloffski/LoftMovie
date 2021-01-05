package com.example.popular.presentation



import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
import com.example.popular.data.FilmDataSource
import com.example.popular.data.FilmDataSourceFactory
import com.example.popular.data.PopularRepository
import com.example.popular.data.PopularRepositoryImpl

import com.example.popular.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class PopularFragmentViewModel @Inject constructor(
    private val repository: PopularRepository
) : ViewModel() {

    var filmDataSourceFactory: FilmDataSourceFactory

    val popularFilm: MutableLiveData<Resource<FilmResultResponse>> = MutableLiveData()

    var filmList: LiveData<PagedList<Film>>

    init {
        filmDataSourceFactory = FilmDataSourceFactory(repository)
        filmList = LivePagedListBuilder(filmDataSourceFactory, FilmDataSourceFactory.pagedListConfig()).build()
    }

    fun getState(): LiveData<Resource<FilmResultResponse>> = Transformations.switchMap(
        filmDataSourceFactory.liveData,
        FilmDataSource::state
    )

    fun listIsEmpty(): Boolean {
        return filmList.value?.isEmpty() ?: true
    }



}









