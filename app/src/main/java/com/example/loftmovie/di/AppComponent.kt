package com.example.loftmovie.di

import android.app.Application
import android.content.Context
import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.AppScope
import com.example.core_api.providers.AppProvider
import com.example.loftmovie.BaseApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    dependencies = [],
    modules =[]
)
interface AppComponent: AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also {
                    appComponent = it
                }
        }
    }
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}