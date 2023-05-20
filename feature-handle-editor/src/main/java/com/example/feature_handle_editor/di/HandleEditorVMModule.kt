package com.example.feature_handle_editor.di

import androidx.lifecycle.ViewModel
import com.example.common.di.ViewModelModule
import com.example.common.di.annotations.ViewModelKey
import com.example.feature_handle_editor.HandleEditorViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class HandleEditorVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(HandleEditorViewModel::class)
    abstract fun provideHandleEditorViewModel(viewModel: HandleEditorViewModel): ViewModel
}
