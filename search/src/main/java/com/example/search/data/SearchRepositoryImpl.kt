package com.example.search.data

import com.example.core.network.service.MovieService
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val service: MovieService
): SearchRepository {


}