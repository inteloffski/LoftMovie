package com.example.core_api.providers

import com.example.core.db.LoftMovieDBContract
import com.example.core.db.dao.FilmDao
import com.example.core.network.service.MovieService

interface DataProvider {

    fun provideNetworkService(): MovieService

    fun provideFilmDao(): FilmDao

    fun provideDatabase(): LoftMovieDBContract
}