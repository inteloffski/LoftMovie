package com.example.popular.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.db.dao.FilmDao
import com.example.core.network.responses.Film
import com.example.core.network.responses.FilmResultResponse
import com.example.core.network.service.MovieService
import com.example.popular.utils.Resource
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: FilmDao,
) : PopularRepository {

    var filmDataSourceFactory: FilmDataSourceFactory

    private var filmList: LiveData<PagedList<Film>>

    init {
        filmDataSourceFactory = FilmDataSourceFactory(service, dao)
        filmList = LivePagedListBuilder(filmDataSourceFactory,
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
        return filmList.value?.isEmpty() ?: true
    }

    override fun getFilmList(): LiveData<PagedList<Film>> {
        return filmList
    }

    override fun observeLocalPagedSets(): LiveData<PagedList<Film>> {
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