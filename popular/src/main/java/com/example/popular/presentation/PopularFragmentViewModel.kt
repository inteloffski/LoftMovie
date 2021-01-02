package com.example.popular.presentation



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
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

    init {
        val dataSourceFactory = FilmDataSourceFactory(repository)
        var filmList = LivePagedListBuilder<Int, Film>(dataSourceFactory, FilmDataSourceFactory.pagedListConfig()).build()
    }

    fun getPopularFilm(page: Int) = viewModelScope.launch{
        popularFilm.postValue(Resource.Loading())
        val response = repository.fetchPopularFilms(page)
        popularFilm.postValue(handlePopularFilmResponse(response))
    }

    private fun handlePopularFilmResponse(response: Response<FilmResultResponse>): Resource<FilmResultResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}









