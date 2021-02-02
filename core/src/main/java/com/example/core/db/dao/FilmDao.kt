package com.example.core.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.core.network.responses.FilmDTO.Film


@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(film: List<Film>)

    @Query("SELECT * FROM filmEntity")
    fun getPagedFilm(): DataSource.Factory<Int, Film>

    @Query("SELECT * FROM filmEntity")
    fun getFilmAll(): LiveData<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFilm(film: Film): Long

    @Delete
    suspend fun deleteFilm(film: Film)





}