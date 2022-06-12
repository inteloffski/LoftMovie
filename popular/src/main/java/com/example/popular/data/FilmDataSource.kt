package com.example.popular.data



import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.service.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmDataSource @Inject constructor(
    private val service: MovieService
) : RxPagingSource<Int, FilmDTO>() {

    override fun getRefreshKey(state: PagingState<Int, FilmDTO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }


    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FilmDTO>> {
        val page = params.key ?: 1

        return service.getPopularFilms(page)
            .subscribeOn(Schedulers.io())
            .map { response ->
                toLoadResult(
                    listFilm = response.items,
                    prevKey = if(page == 1) null else page - 1,
                    nextKey = if(response.totalPages == page) null else page + 1
                )
            }
            .onErrorReturn { exception ->
                LoadResult.Error(exception)
            }


    }

    private fun toLoadResult(
        listFilm: List<FilmDTO>,
        prevKey: Int?,
        nextKey: Int?
    ): LoadResult<Int, FilmDTO> =
        LoadResult.Page(
            data = listFilm,
            prevKey = prevKey,
            nextKey = nextKey
        )

}
