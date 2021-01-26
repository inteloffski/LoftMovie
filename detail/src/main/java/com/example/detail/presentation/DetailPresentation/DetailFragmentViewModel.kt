package com.example.detail.presentation.DetailPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.Film
import com.example.core.utils.Resource
import com.example.detail.data.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(
    private val repository: DetailRepository,
) : ViewModel() {

    private val _stateListActors: MutableLiveData<Resource<Crew>> = MutableLiveData()

    val stateListActors: LiveData<Resource<Crew>> = _stateListActors

    private val _selectedMovieLiveData: MutableLiveData<Film> = MutableLiveData()

    val selectedMovieLiveData: LiveData<Film> = _selectedMovieLiveData


    fun getState(): LiveData<Resource<Crew>> {
        return stateListActors
    }


    fun selectedMovie(film: Film) {
        _stateListActors.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _selectedMovieLiveData.postValue(film)
                val response = repository.fetchActors(film.id)
                if (response.isSuccessful) {
                    response.body()?.let { resultResponse ->
                        withContext(Dispatchers.Main) {
                            _stateListActors.postValue(Resource.Success(resultResponse))
                        }
                    }
                } else {
                    _stateListActors.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException -> _stateListActors.postValue(Resource.Error("No internet connection"))
                }
            }

        }

    }


}