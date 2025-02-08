package com.samarbaeffruslan.di

import com.samarbaeffruslan.core_api.providers.ProvidersFacade
import com.samarbaeffruslan.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [MainActivityModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MainActivityComponent{

    companion object {

        fun create(providersFacade: ProvidersFacade): MainActivityComponent {
            return DaggerMainActivityComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(activity: MainActivity)
}