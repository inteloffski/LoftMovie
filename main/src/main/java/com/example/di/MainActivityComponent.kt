package com.example.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@FeatureScope
@Component
interface MainActivityComponent{

    fun inject(activity: MainActivity)
}