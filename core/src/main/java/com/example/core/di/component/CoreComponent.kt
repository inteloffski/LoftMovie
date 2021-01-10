package com.example.core.di.component

import com.example.core.di.modules.DatabaseModule
import com.example.core.di.modules.NetworkModule
import com.example.core_api.providers.AppProvider
import com.example.core_api.providers.DataProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [
        NetworkModule::class,
        DatabaseModule::class]
)
interface CoreComponent : DataProvider {

}