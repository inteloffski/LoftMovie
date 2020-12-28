package com.example.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.responses.Film
import com.example.core.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val service: MovieService
): SearchRepository {


}