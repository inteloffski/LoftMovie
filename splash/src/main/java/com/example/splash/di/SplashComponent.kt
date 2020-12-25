package com.example.splash.di

import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.splash.presentation.SplashFragment
import dagger.Component


@Component(
    dependencies = [ProvidersFacade::class],
    modules = [SplashFragmentModule::class]
)
interface SplashComponent {
    companion object {

        fun create(providersFacade: ProvidersFacade): SplashComponent {
            return DaggerSplashComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }

        fun injectFragment(fragment: SplashFragment): SplashComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }
    }


    fun inject(fragment: SplashFragment)
}
