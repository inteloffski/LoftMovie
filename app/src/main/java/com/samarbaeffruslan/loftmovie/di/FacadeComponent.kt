package com.samarbaeffruslan.loftmovie.di

import android.app.Application
import com.samarbaeffruslan.core.di.component.DaggerCoreComponent
import com.samarbaeffruslan.core_api.providers.AppProvider
import com.samarbaeffruslan.core_api.providers.DataProvider
import com.samarbaeffruslan.core_api.providers.ProvidersFacade
import com.samarbaeffruslan.loftmovie.BaseApp
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        DataProvider::class],
    modules = [NavigationModule::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {
        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .dataProvider(DaggerCoreComponent.builder().appProvider(AppComponent.create(application)).build())
                .build()
    }

    fun inject(app: BaseApp)

}