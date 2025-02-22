package com.samarbaeffruslan.detail.data

import androidx.lifecycle.MutableLiveData
import com.samarbaeffruslan.core.db.dao.FilmDao
import com.samarbaeffruslan.core.db.dao.entities.FilmEntity
import com.samarbaeffruslan.core.network.responses.ActorsDTO.Crew
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.network.responses.videoDTO.Video
import com.samarbaeffruslan.core.network.service.MovieService
import retrofit2.Response
import javax.inject.Inject


class DetailRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: FilmDao
) : DetailRepository {

    private var getSelectedMovie: MutableLiveData<FilmDTO> = MutableLiveData()

    override suspend fun fetchActors(id: Int): Response<Crew> =
        service.getListActors(id)

    override suspend fun fetchVideo(id: Int): Response<Video> =
        service.getListTrailer(id)

    override suspend fun upsertFilm(filmEntity: FilmEntity) {
        dao.insertFilm(filmEntity)
    }


    override fun selectedMovieLiveData(filmDTO: FilmDTO) {
        getSelectedMovie.postValue(filmDTO)
    }






}