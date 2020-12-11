package com.example.loftmovie.di

import android.app.Application
import android.content.Context
import com.example.core.di.component.CoreComponent
import com.example.core.di.component.DaggerCoreComponent
import com.example.core_api.providers.AppProvider
import com.example.core_api.providers.DataProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.di.DaggerMainActivityComponent
import com.example.loftmovie.BaseApp
import dagger.BindsInstance
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