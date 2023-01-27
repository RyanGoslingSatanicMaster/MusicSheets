package com.example.core_synth

import android.util.Log

internal class TestSynthesizerImpl: Synthesizer {

    override suspend fun play() {
        Log.v(TAG, "play() called")
    }

    override suspend fun stop() {
        Log.v(TAG, "stop() called")
    }

    override suspend fun isPlaying(): Boolean {
        Log.v(TAG, "isPlaying() called")
        return true
    }

    override suspend fun setFrequency(hz: Hz) {
        Log.v(TAG, "setFrequency() called with $hz Hz")
    }

    override suspend fun setVolume(db: Db) {
        Log.v(TAG, "setVolume() called with $db Db")
    }

    override suspend fun setWaveTable(waveTable: WaveTable) {
        Log.v(TAG, "setWaveTable() called with $waveTable")
    }

    companion object {

        private const val TAG = "TEST_SYNTH_TAG"

    }
}
