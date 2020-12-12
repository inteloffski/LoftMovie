package com.example.search.di

import com.example.core.di.scopes.FeatureScope
import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.search.presentation.SearchFragment
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