package com.example.detail.data


import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.Film
import retrofit2.Response

interface DetailRepository {
    suspend fun fetchActors(
        id:Int
    ): Response<Crew>


    fun selectedMovieLiveData(film: Film)

}