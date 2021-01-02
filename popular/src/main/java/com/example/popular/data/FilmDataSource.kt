package com.example.popular.data


import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
import com.example.popular.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmDataSource @Inject constructor(
    private val repository: PopularRepository
) : PageKeyedDataSource<Int, Film>() {

    val state: MutableLiveData<Resource<FilmResultResponse>> = MutableLiveData()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Film>,
    ) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {


    }



}
