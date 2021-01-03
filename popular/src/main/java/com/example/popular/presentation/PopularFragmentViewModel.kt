package com.example.popular.presentation



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
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

    val popularFilm: MutableLiveData<Resource<FilmResultResponse>> = MutableLiveData()

    var filmList: LiveData<PagedList<Film>>

    init {
        val dataSourceFactory = FilmDataSourceFactory(repository)
        filmList = LivePagedListBuilder(dataSourceFactory, FilmDataSourceFactory.pagedListConfig()).build()
    }



}









