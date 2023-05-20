package com.example.feature_handle_editor

import com.example.core_synth.Db
import com.example.core_synth.Hz
import com.example.core_synth.WaveTable

data class SynthesizerData(
    val isPlaying: Boolean,
    val waveTable: WaveTable,
    val freq: Hz,
    val volume: Db
)
