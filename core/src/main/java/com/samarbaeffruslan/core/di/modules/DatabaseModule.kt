package com.samarbaeffruslan.core.di.modules

import android.content.Context
import androidx.room.Room
import com.samarbaeffruslan.core.db.LoftMovieDB
import com.samarbaeffruslan.core.db.LoftMovieDBContract
import com.samarbaeffruslan.core.db.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DatabaseModule {
    companion object{
        @Provides
        @Singleton
        fun provideLoftMovieDatabase(context: Context): LoftMovieDBContract{
            return Room.databaseBuilder(
                context,
                LoftMovieDB::class.java,
                "LoftMovieDB"
            ).fallbackToDestructiveMigration().build()
        }

        @Provides
        @Reusable
        fun provideFilmDao(loftMovieDBContract: LoftMovieDBContract): FilmDao{
            return loftMovieDBContract.filmDao()
        }
    }
}