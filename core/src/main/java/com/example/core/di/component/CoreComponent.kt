package com.example.core.di.component

import android.content.Context
import com.example.core.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class
]
)
interface CoreComponent {

}