package com.example.loftmovie

import android.app.Application
import android.content.Context
import com.example.core.di.component.CoreComponent
import com.example.core.di.component.DaggerCoreComponent
import com.example.loftmovie.di.DaggerAppComponent

class BaseApp: Application() {

    lateinit var coreComponent: CoreComponent







    override fun onCreate() {
        super.onCreate()
        initCoreDI()
        initAppDI()
    }


    private fun initCoreDI() {
        coreComponent = DaggerCoreComponent
            .builder()
            .build()
    }

    private fun initAppDI(){
        DaggerAppComponent.builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }


}