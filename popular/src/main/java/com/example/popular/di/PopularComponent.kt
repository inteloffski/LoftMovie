package com.example.popular.di


import com.example.core.di.scopes.FeatureScope
import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.popular.presentation.PopularFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [PopularFragmentModule::class]
)
interface PopularComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): PopularComponent {
            return DaggerPopularComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }

        fun injectFragment(fragment: PopularFragment): PopularComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }
    }

    fun inject(fragment: PopularFragment)
}