package com.samarbaeffruslan.favorite.di

import com.samarbaeffruslan.core_api.providers.AppWithFacade
import com.samarbaeffruslan.core_api.providers.ProvidersFacade
import com.samarbaeffruslan.favorite.presentation.FavoriteFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [FavoriteFragmentModule::class]
)
interface FavoriteComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): FavoriteComponent {
            return DaggerFavoriteComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }

        fun injectFragment(fragment: FavoriteFragment): FavoriteComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }
    }


    fun inject(fragment: FavoriteFragment)
}