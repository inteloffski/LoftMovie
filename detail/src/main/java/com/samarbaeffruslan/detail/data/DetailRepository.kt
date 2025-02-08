package com.samarbaeffruslan.detail.data



import com.samarbaeffruslan.core.db.dao.entities.FilmEntity
import com.samarbaeffruslan.core.network.responses.ActorsDTO.Crew
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.network.responses.videoDTO.Video
import retrofit2.Response

interface DetailRepository {
    suspend fun fetchActors(
        id:Int
    ): Response<Crew>

    suspend fun fetchVideo(
        id: Int
    ): Response<Video>

    suspend fun upsertFilm(filmEntity: FilmEntity)

    fun selectedMovieLiveData(filmDTO: FilmDTO)

}