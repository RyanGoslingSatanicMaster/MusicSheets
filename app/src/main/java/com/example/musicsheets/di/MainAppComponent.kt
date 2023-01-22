package com.example.musicsheets.di

import android.content.Context
import com.example.common.BaseApp
import com.example.common.di.ApplicationComponent
import com.example.common.di.annotations.ApplicationContext
import com.example.feature_motion_music.di.MotionMusicAppComponent
import com.example.musicsheets.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainAppModule::class])
interface MainAppComponent: ApplicationComponent, MotionMusicAppComponent {

    fun inject(app: App)

    @Component.Builder
    abstract class Builder{

        @BindsInstance
        abstract fun bindApp(app: App): Builder

        abstract fun build(): MainAppComponent

    }
}
