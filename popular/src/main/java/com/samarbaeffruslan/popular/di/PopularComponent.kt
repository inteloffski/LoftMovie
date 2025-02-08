package com.samarbaeffruslan.popular.di


import com.samarbaeffruslan.core.di.scopes.FeatureScope
import com.samarbaeffruslan.core_api.providers.AppWithFacade
import com.samarbaeffruslan.core_api.providers.ProvidersFacade
import com.samarbaeffruslan.popular.presentation.PopularFragment
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