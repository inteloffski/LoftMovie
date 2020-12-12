package com.example.core_api.providers

import android.content.Context


interface AppProvider {

    fun provideContext(): Context
}