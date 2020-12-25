package com.example.core_api.providers

import com.example.core.network.service.MovieService

interface DataProvider {

    fun provideNetworkService(): MovieService
}