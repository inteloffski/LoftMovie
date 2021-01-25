package com.example.detail.di


import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.detail.presentation.DetailActorsPresentation.DetailActorsFragment
import com.example.detail.presentation.DetailDescriptionPresentation.DetailDescriptionFragment
import com.example.detail.presentation.DetailPresentation.DetailFragment
import dagger.Component


@Component(
    dependencies = [ProvidersFacade::class],
    modules = [DetailFragmentModule::class]
)
interface DetailComponent {
    companion object {

        fun create(providersFacade: ProvidersFacade): DetailComponent {
            return DaggerDetailComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }

        fun injectFragment(fragment: DetailFragment): DetailComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }

        fun injectFragmentDescription(fragment: DetailDescriptionFragment): DetailComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }

        fun injectFragmentActors(fragment: DetailActorsFragment): DetailComponent  {
            val component = create((fragment.activity?.application
                    as AppWithFacade).getFacade())
            component.inject(fragment)
            return component
        }
    }

    fun inject(fragment: DetailFragment)

    fun inject(fragment: DetailDescriptionFragment)

    fun inject(fragment: DetailActorsFragment)
}