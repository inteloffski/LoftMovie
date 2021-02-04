package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.db.dao.entities.FilmEntity


@Database(entities = [
    FilmEntity::class
], version = 1)
abstract class LoftMovieDB: RoomDatabase(), LoftMovieDBContract