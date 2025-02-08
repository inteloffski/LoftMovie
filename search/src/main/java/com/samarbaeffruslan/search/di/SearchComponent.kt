package com.samarbaeffruslan.search.di

import com.samarbaeffruslan.core.di.scopes.FeatureScope
import com.samarbaeffruslan.core_api.providers.AppWithFacade
import com.samarbaeffruslan.core_api.providers.ProvidersFacade
import com.samarbaeffruslan.search.presentation.SearchFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [SearchFragmentModule::class]
)
interface SearchComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): SearchComponent {
            return DaggerSearchComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }

        fun injectFragment(fragment: SearchFragment): SearchComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }
    }


    fun inject(fragment: SearchFragment)
}