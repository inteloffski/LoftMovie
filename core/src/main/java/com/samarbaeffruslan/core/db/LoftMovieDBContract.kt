package com.samarbaeffruslan.core.db

import com.samarbaeffruslan.core.db.dao.FilmDao

interface LoftMovieDBContract {
    fun filmDao(): FilmDao
}