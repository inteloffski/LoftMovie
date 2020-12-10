package com.example.search.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.search.presentation.SearchFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [SearchFragmentModule::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
}