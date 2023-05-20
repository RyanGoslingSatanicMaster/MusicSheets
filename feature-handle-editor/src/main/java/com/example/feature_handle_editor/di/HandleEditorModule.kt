package com.example.feature_handle_editor.di

import com.example.common.di.annotations.PerActivity
import com.example.core_synth.Synthesizer
import dagger.Module
import dagger.Provides

@Module
object HandleEditorModule {


    @PerActivity
    @JvmStatic
    @Provides
    fun provideSynthController(
    ): Synthesizer = Synthesizer.getTestSynth()

}
