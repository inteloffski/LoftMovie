package com.example.popular.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.popular.presentation.PopularFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class]
)
interface PopularComponent {
    fun inject(fragment: PopularFragment)
}