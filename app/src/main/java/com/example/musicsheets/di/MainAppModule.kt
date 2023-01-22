package com.example.musicsheets.di

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.common.BaseApp
import com.example.common.NavigateToHost
import com.example.common.di.annotations.ApplicationContext
import com.example.musicsheets.App
import com.example.musicsheets.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MainAppModule {

    @Singleton
    @Provides
    @JvmStatic
    @ApplicationContext
    fun getApplicationContext(app: App): Context = app


    @Singleton
    @Provides
    @JvmStatic
    fun getBaseApp(app: App): BaseApp = app


    @Singleton
    @Provides
    @JvmStatic
    fun getApp(app: App): Application = app

    @Singleton
    @Provides
    @JvmStatic
    fun getNavHost(): NavigateToHost = object : NavigateToHost{
        override fun invoke(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
