package com.example.loftmovie.di

import android.content.Context
import com.example.loftmovie.BaseApp
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    fun provideContext(app: BaseApp): Context = app.applicationContext
}