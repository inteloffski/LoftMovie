package com.example.core.network.service

import com.example.core.network.responses.FilmDetailsResponse
import com.example.core.network.responses.FilmResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    //Search for movies.
    @GET("/search/movie")
    suspend fun searchFilm(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): FilmResultResponse

    //Get a list of the current popular movies on TMDb. This list updates daily.
    @GET("/movie/popular")
    suspend fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): FilmResultResponse

    //Get the top rated movies on TMDb.
    @GET("/movie/top_rated")
    suspend fun getTopRatedFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): FilmResultResponse

    //Get the primary information about a movie.
    @GET("/movie/{movie_id}")
    suspend fun getDetailsFilm(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): FilmDetailsResponse






}