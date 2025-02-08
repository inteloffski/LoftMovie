package com.samarbaeffruslan.core.di.component

import com.samarbaeffruslan.core.di.modules.DatabaseModule
import com.samarbaeffruslan.core.di.modules.NetworkModule
import com.samarbaeffruslan.core_api.providers.AppProvider
import com.samarbaeffruslan.core_api.providers.DataProvider
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