package com.example.core.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.core.db.dao.entities.FilmEntity


@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filmEntityDTO: List<FilmEntity>)

    @Query("SELECT * FROM filmEntity")
    fun getPagedFilm(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmEntity WHERE isFavorite = 1")
    fun getFilmAll(): LiveData<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFilm(filmEntity: FilmEntity): Long

    @Delete
    suspend fun deleteFilm(filmEntity: FilmEntity)





}