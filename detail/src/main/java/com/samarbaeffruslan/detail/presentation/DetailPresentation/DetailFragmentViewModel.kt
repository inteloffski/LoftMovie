package com.samarbaeffruslan.detail.presentation.DetailPresentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarbaeffruslan.core.db.dao.entities.FilmEntity
import com.samarbaeffruslan.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.samarbaeffruslan.core.network.responses.ActorsDTO.Crew
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.network.responses.videoDTO.Video
import com.samarbaeffruslan.core.utils.Resource
import com.samarbaeffruslan.core.utils.SingleLiveEvent
import com.samarbaeffruslan.detail.data.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject
import com.samarbaeffruslan.asset.R as Asset

class DetailFragmentViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val mapper: FilmDTOFilmEntityMapper
) : ViewModel() {

    private val _stateListActors: MutableLiveData<Resource<Crew>> = MutableLiveData()

    val stateListActors: LiveData<Resource<Crew>> = _stateListActors

    private val _selectedMovieLiveData: MutableLiveData<FilmDTO> = MutableLiveData()

    val selectedMovieLiveData: LiveData<FilmDTO> = _selectedMovieLiveData

    private val _videoLiveData: MutableLiveData<Resource<Video>>? = SingleLiveEvent()

    var videoLiveData: LiveData<Resource<Video>>? = _videoLiveData



    fun getTrailerVideo(){
        _videoLiveData?.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = _selectedMovieLiveData.value?.id?.let { repository.fetchVideo(it) }
                if(response?.isSuccessful!!){
                    response.body()?.let {videoResult ->
                        withContext(Dispatchers.Main){
                            _videoLiveData?.postValue(Resource.Success(videoResult))
                        }
                    }
                } else {
                    _videoLiveData?.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                when(e){
                    is UnknownHostException -> _videoLiveData?.postValue(Resource.Error(Asset.string.no_internet_connection.toString()))
                }
            }
        }
    }

    fun selectedMovie(filmDTO: FilmDTO) {
        _stateListActors.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _selectedMovieLiveData.postValue(filmDTO)
                val response = repository.fetchActors(filmDTO.id)
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
                    is UnknownHostException -> _stateListActors.postValue(Resource.Error(Asset.string.no_internet_connection.toString()))
                }
            }
        }
    }

    private fun saveFilm(filmEntity: FilmEntity) = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main){
            repository.upsertFilm(filmEntity)
        }
    }

    fun savedFilm(){
        selectedMovieLiveData.value?.let {filmDto ->
            saveFilm(mapper.map(filmDto).also { filmEntity ->
                filmEntity.isFavorite = true
            })
        }
    }


}