package com.example.core.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.network.responses.FilmDTO.Film


@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(film: List<Film>)

    @Query("SELECT * FROM filmEntity")
    fun getPagedFilm(): DataSource.Factory<Int, Film>

    @Query("SELECT * FROM filmEntity")
    fun getFilmAll(): LiveData<List<Film>>





}