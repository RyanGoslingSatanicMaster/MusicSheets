package com.example.feature_motion_music.di

import androidx.lifecycle.ViewModel
import com.example.common.di.ViewModelModule
import com.example.common.di.annotations.ViewModelKey
import com.example.feature_motion_music.MotionMusicViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MotionMusicVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MotionMusicViewModel::class)
    abstract fun provideMotionMusicVM(viewModel: MotionMusicViewModel): ViewModel
}
