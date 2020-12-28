package com.example.popular.presentation



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.responses.FilmResultResponse
import com.example.popular.data.PopularRepository

import com.example.popular.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class PopularFragmentViewModel @Inject constructor(
    private val repository: PopularRepository,
) : ViewModel() {

    val popularFilm: MutableLiveData<Resource<FilmResultResponse>> = MutableLiveData()

    init {
        getPopularFilm()
    }

    fun getPopularFilm() = viewModelScope.launch {
        popularFilm.postValue(Resource.Loading())
        val response = repository.fetchPopularFilms()
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









