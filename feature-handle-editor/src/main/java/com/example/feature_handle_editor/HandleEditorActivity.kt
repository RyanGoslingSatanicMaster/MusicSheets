package com.example.feature_handle_editor

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common.BaseActivity
import com.example.common.BaseApp
import com.example.common.NavigateToHost
import com.example.core_synth.Db
import com.example.core_synth.Hz
import com.example.core_synth.WaveTable
import com.example.core_synth.dB
import com.example.feature_handle_editor.di.HandleEditorAppComponent
import com.example.feature_handle_editor.di.HandleEditorComponent
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

class HandleEditorActivity : BaseActivity() {

    private lateinit var component: HandleEditorComponent

    private val viewModel by lazy {
        getViewModel<HandleEditorViewModel>()
    }

    @Inject
    lateinit var navigateToHost: NavigateToHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandleEditorLayout()
        }
    }

    override fun inject() {
        component =
            ((application as BaseApp)
                .getAppComponent() as HandleEditorAppComponent)
                .getHandleEditorComponentComponentBuilder().build()
        component.inject(this)
    }

    @Composable
    fun HandleEditorLayout(){
        val synthState by viewModel.synthesizerState.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { if (synthState.isPlaying) viewModel.stop() else viewModel.play()}) {
                Text(text = if (synthState.isPlaying) "Stop" else "Play")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { viewModel.setWaveTable(WaveTable.SINE) }) {
                    Text(text = "SINE")
                }
                Button(onClick = { viewModel.setWaveTable(WaveTable.TRIANGLE) }) {
                    Text(text = "TRIANGLE")
                }
                Button(onClick = { viewModel.setWaveTable(WaveTable.SAW) }) {
                    Text(text = "SAW")
                }
                Button(onClick = { viewModel.setWaveTable(WaveTable.SQUARE) }) {
                    Text(text = "SQUARE")
                }
            }
            PitchControl(synthState.freq)
            VolumeControl(synthState.volume)
        }
    }

    @Composable
    fun PitchControl(freq: Hz){
        Text(text = "Frequency")
        Slider(value = freq.value, onValueChange = {viewModel.setFrequency(it.Hz)}, valueRange = 40f..3000f)
    }
    
    @Composable
    fun VolumeControl(db: Db){
        Text(text = "Volume")
        Slider(value = db.value, onValueChange = {viewModel.setVolume(it.dB)}, valueRange = -60f..0f)
    }
}
