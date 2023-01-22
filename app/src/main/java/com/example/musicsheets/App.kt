package com.example.musicsheets

import com.example.common.BaseApp
import com.example.common.di.ApplicationComponent
import com.example.musicsheets.di.DaggerMainAppComponent
import com.example.musicsheets.di.MainAppComponent


class App: BaseApp() {

    lateinit var component: MainAppComponent

    override fun inject() {
        component = DaggerMainAppComponent.builder().bindApp(this).build()
    }

    override fun getAppComponent(): ApplicationComponent {
        return component
    }


}
