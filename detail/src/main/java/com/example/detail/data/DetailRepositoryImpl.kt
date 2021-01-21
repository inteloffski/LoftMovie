package com.example.detail.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.network.responses.ActorsDTO.Actors
import com.example.core.network.responses.FilmDTO.Film
import com.example.core.network.service.MovieService
import retrofit2.Response
import javax.inject.Inject


class DetailRepositoryImpl @Inject constructor(
    private val service: MovieService,
) : DetailRepository {

    private var getSelectedMovie: MutableLiveData<Film> = MutableLiveData()

    override suspend fun fetchActors(id: Int): Response<Actors> =
        service.getListActors(id)

    override fun getSelectedMovie(): MutableLiveData<Film> {
        return getSelectedMovie
    }

    override fun selectedMovieLiveData(film: Film) {
        getSelectedMovie.postValue(film)
    }


}