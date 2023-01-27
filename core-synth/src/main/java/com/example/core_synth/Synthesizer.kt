package com.example.core_synth

interface Synthesizer {
    suspend fun play()
    suspend fun stop()
    suspend fun isPlaying(): Boolean
    suspend fun setFrequency(hz: Hz)
    suspend fun setVolume(db: Db)
    suspend fun setWaveTable(wavetable: WaveTable)

    companion object{
        fun getTestSynth(): Synthesizer = TestSynthesizerImpl()
    }
}
