package com.example.feature_handle_editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_synth.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HandleEditorViewModel @Inject constructor(
    private val synthesizer: Synthesizer
) : ViewModel() {

    private val _synthesizerState = MutableStateFlow(SynthesizerData(
        isPlaying = false,
        waveTable = WaveTable.SINE,
        freq = 300f.Hz,
        volume = (-25f).dB
    ))
    val synthesizerState = _synthesizerState.asStateFlow()

    fun play() {
        viewModelScope.launch {
            synthesizer.play()
            _synthesizerState.update {
                _synthesizerState.value.copy(isPlaying = true)
            }
        }
    }

    fun stop() {
        viewModelScope.launch {
            synthesizer.stop()
            _synthesizerState.update {
                _synthesizerState.value.copy(isPlaying = false)

            }
        }
    }

    fun setVolume(db: Db) {
        viewModelScope.launch {
            synthesizer.setVolume(db)
            _synthesizerState.update {
                _synthesizerState.value.copy(volume = db)
            }
        }
    }

    fun setWaveTable(waveTable: WaveTable) {
        viewModelScope.launch {
            synthesizer.setWaveTable(waveTable)
            _synthesizerState.update {
                _synthesizerState.value.copy(waveTable = waveTable)
            }
        }
    }

    fun setFrequency(freq: Hz){
        viewModelScope.launch {
            synthesizer.setFrequency(freq)
            _synthesizerState.update {
                _synthesizerState.value.copy(freq = freq)
            }
        }
    }

    fun isPlaying(){
        viewModelScope.launch {
            viewModelScope.launch {
                val isPlaying = synthesizer.isPlaying()
                _synthesizerState.update {
                    _synthesizerState.value.copy(isPlaying = isPlaying)
                }
            }
        }
    }
}
