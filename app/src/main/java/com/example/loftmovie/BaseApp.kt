package com.example.loftmovie

import android.app.Application
import android.content.Context
import com.example.core.di.component.CoreComponent
import com.example.core.di.component.DaggerCoreComponent
import com.example.core_api.providers.AppWithFacade
import com.example.core_api.providers.ProvidersFacade
import com.example.loftmovie.di.DaggerAppComponent
import com.example.loftmovie.di.FacadeComponent

class BaseApp: Application(), AppWithFacade {

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }




    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also { facadeComponent = it }
    }


}