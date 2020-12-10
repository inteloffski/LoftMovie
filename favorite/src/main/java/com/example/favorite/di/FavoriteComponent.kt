package com.example.favorite.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.favorite.presentation.FavoriteFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteFragmentModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
}