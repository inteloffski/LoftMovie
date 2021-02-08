package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.db.dao.FilmDao
import com.example.core.db.dao.entities.FilmEntity
import com.example.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.network.service.MovieService
import com.example.core.utils.Resource
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: FilmDao,
    private val filmDTOFilmEntityMapper: FilmDTOFilmEntityMapper
) : PopularRepository {

    private var filmDataSourceFactory: FilmDataSourceFactory = FilmDataSourceFactory(service, dao, filmDTOFilmEntityMapper)

    //private val mapper = Mapper()


    private var filmDTOList: LiveData<PagedList<FilmDTO>>

    init {
        filmDTOList = LivePagedListBuilder(filmDataSourceFactory,
            FilmDataSourceFactory.pagedListConfig()).build()
    }


    override suspend fun fetchPopularFilms(page: Int): Response<FilmResultResponse> =
        service.getPopularFilms(page)


    override suspend fun fetchTopRatedFilms(page: Int): Response<FilmResultResponse> =
        service.getTopRatedFilms(page)



    override fun getState(): LiveData<Resource<FilmResultResponse>> = Transformations.switchMap(
        filmDataSourceFactory.liveData,
        FilmDataSource::state
    )


    override fun listIsEmpty(): Boolean {
        return filmDTOList.value?.isEmpty() ?: true
    }

    override fun getFilmList(): LiveData<PagedList<FilmDTO>> {
        return filmDTOList
    }

    override fun observeLocalPagedSets(): LiveData<PagedList<FilmEntity>> {
        val dataSourceFactory =
            dao.getPagedFilm()



        return LivePagedListBuilder(
            dataSourceFactory,
            FilmDataSourceFactory.pagedListConfig()
        ).build()
    }

    override fun refresh(){
        filmDataSourceFactory.liveData.value?.invalidate()
    }


}