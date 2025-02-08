package com.samarbaeffruslan.core_api.providers

import com.samarbaeffruslan.core.db.LoftMovieDBContract
import com.samarbaeffruslan.core.db.dao.FilmDao
import com.samarbaeffruslan.core.network.service.MovieService

interface DataProvider {

    fun provideNetworkService(): MovieService

    fun provideFilmDao(): FilmDao

    fun provideDatabase(): LoftMovieDBContract
}