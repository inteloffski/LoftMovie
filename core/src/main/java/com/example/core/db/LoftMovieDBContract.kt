package com.example.core.db

import com.example.core.db.dao.FilmDao

interface LoftMovieDBContract {
    fun filmDao(): FilmDao
}