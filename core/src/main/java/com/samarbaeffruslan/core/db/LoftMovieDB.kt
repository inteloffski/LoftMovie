package com.samarbaeffruslan.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samarbaeffruslan.core.db.dao.entities.FilmEntity


@Database(entities = [
    FilmEntity::class
], version = 2)
abstract class LoftMovieDB: RoomDatabase(), LoftMovieDBContract