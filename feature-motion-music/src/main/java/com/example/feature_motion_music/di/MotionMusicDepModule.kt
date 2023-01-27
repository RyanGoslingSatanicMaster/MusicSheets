package com.example.feature_motion_music.di

import com.example.feature_motion_music.MotionMusicController
import com.example.feature_motion_music.MotionMusicControllerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MotionMusicDepModule {

    @Binds
    abstract fun provideMotionMusicController(controller: MotionMusicControllerImpl): MotionMusicController
}
