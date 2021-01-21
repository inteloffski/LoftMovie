package com.example.detail.presentation.DetailPresentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.responses.ActorsDTO.Actors
import com.example.core.network.responses.FilmDTO.Film
import com.example.detail.data.DetailRepository
import com.example.detail.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(
    private val repository: DetailRepository,
) : ViewModel() {

    private val stateListActors: MutableLiveData<Resource<Actors>> = MutableLiveData()

    fun getSelectedMovie() = repository.getSelectedMovie()

    fun selectedMovieLiveData(film: Film) = repository.selectedMovieLiveData(film)

    fun selectedMovie(film: Film) {
        stateListActors.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchActors(film.id)
                if (response.isSuccessful) {
                    response.body()?.let { resultResponse ->
                        withContext(Dispatchers.Main) {
                            stateListActors.postValue(Resource.Success(resultResponse))
                        }
                    }
                } else {
                    stateListActors.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException -> stateListActors.postValue(Resource.Error("No internet connection"))
                }
            }

        }

    }
}