package com.example.feature_handle_editor.di

import androidx.core.app.ComponentActivity
import com.example.common.di.annotations.PerActivity
import com.example.feature_handle_editor.HandleEditorActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [HandleEditorModule::class, HandleEditorVMModule::class])
interface HandleEditorComponent {

    fun inject(activity: HandleEditorActivity)


    @Subcomponent.Builder
    interface Builder{

        fun build(): HandleEditorComponent

    }
}
