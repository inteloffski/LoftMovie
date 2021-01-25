package com.example.detail.data


import androidx.lifecycle.MutableLiveData
import com.example.core.network.responses.ActorsDTO.Actors
import com.example.core.network.responses.FilmDTO.Film
import retrofit2.Response

interface DetailRepository {
    suspend fun fetchActors(
        id:Int
    ): Response<Actors>


    fun selectedMovieLiveData(film: Film)

}