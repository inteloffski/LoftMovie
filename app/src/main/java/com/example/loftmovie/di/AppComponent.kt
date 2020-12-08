package com.example.loftmovie.di

import com.example.core.di.component.CoreComponent
import com.example.core.di.scopes.AppScope
import com.example.loftmovie.BaseApp
import dagger.Component



@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules =[AppModule::class]
)
interface AppComponent {
    fun inject(app: BaseApp)
}