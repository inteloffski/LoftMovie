package com.example.favorite.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteComponent)
}