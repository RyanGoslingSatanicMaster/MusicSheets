package com.example.feature_motion_music.di

import androidx.activity.ComponentActivity
import com.example.common.NavigateToHost
import com.example.common.di.annotations.PerActivity
import com.example.feature_motion_music.MotionMusicActivity
import dagger.BindsInstance
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [MotionMusicModule::class, MotionMusicVMModule::class, MotionMusicDepModule::class])
interface MotionMusicComponent {

    fun inject(activity: MotionMusicActivity)

    fun getNavHost(): NavigateToHost

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        fun activity(activity: ComponentActivity): Builder

        fun build(): MotionMusicComponent
    }

}
