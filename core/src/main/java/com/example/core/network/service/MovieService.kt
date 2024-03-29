package com.example.core.network.service

import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.core.network.responses.videoDTO.ResultVideo
import com.example.core.network.responses.videoDTO.Video
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "a567c4221f7842460a3aad764f4243f4"

interface MovieService {

    //Get a list of the current popular movies on TMDb. This list updates daily.
    @GET("3/movie/popular/")
    fun getPopularFilms(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Single<FilmResultResponse>

    //Get the top rated movies on TMDb.
    @GET("/movie/top_rated")
    suspend fun getTopRatedFilms(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US" ,
    ): Response<FilmResultResponse>

    //Get the primary information about a movie.
    @GET("/movie/{movie_id}")
    suspend fun getDetailsFilm(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<FilmResultResponse>

    @GET("3/movie/{movie_id}/credits")
    suspend fun getListActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<Crew>

    @GET("3/movie/{movie_id}/videos")
    suspend fun getListTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<Video>

    @GET("3/search/movie")
    suspend fun searchFilm(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("query") query: String
    ): Response<FilmResultResponse>







}