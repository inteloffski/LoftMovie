package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.network.responses.FilmDTO.Film

@Database(entities = [
    Film::class
], version = 1)
abstract class LoftMovieDB: RoomDatabase(), LoftMovieDBContract