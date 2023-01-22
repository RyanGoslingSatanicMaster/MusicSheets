package com.example.common

import android.app.Application
import com.example.common.di.ApplicationComponent

abstract class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    abstract fun inject()

    abstract fun getAppComponent(): ApplicationComponent
}
