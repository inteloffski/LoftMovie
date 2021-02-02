package com.example.detail.data

import androidx.lifecycle.MutableLiveData
import com.example.core.db.dao.FilmDao
import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.Film
import com.example.core.network.responses.videoDTO.Video
import com.example.core.network.service.MovieService
import retrofit2.Response
import javax.inject.Inject


class DetailRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: FilmDao
) : DetailRepository {

    private var getSelectedMovie: MutableLiveData<Film> = MutableLiveData()

    override suspend fun fetchActors(id: Int): Response<Crew> =
        service.getListActors(id)

    override suspend fun fetchVideo(id: Int): Response<Video> =
        service.getListTrailer(id)

    override suspend fun upsertFilm(film: Film) {
        dao.upsertFilm(film)
    }


    override fun selectedMovieLiveData(film: Film) {
        getSelectedMovie.postValue(film)
    }






}