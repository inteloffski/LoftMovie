package com.example.detail.data



import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.Film
import com.example.core.network.responses.videoDTO.ResultVideo
import com.example.core.network.responses.videoDTO.Video
import retrofit2.Response

interface DetailRepository {
    suspend fun fetchActors(
        id:Int
    ): Response<Crew>

    suspend fun fetchVideo(
        id: Int
    ): Response<Video>

    suspend fun upsertFilm(film: Film)

    fun selectedMovieLiveData(film: Film)

}