package com.example.favorite.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.favorite.presentation.FavoriteFragment
import dagger.Component

@FeatureScope
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