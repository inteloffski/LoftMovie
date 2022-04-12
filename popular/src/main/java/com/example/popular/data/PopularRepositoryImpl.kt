package com.example.popular.data


import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.network.service.MovieService
import io.reactivex.Observable
import javax.inject.Inject
import retrofit2.Response

class PopularRepositoryImpl @Inject constructor(
    private val service: MovieService
) : PopularRepository {

    override fun fetchPopularFilms(): Observable<PagingData<FilmDTO>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FilmDataSource(service) }
        ).observable
    }



    override suspend fun fetchTopRatedFilms(page: Int): Response<FilmResultResponse> {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }


}