package com.example.core_synth

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class NativeSynthesizer : Synthesizer, DefaultLifecycleObserver {

    private var synthesizerHandle: Long = 0

    private val synthesizerMutex = Object()

    private external fun create(): Long

    private external fun delete(synthesizerHandle: Long)

    private external fun play(synthesizerHandle: Long)

    private external fun stop(synthesizerHandle: Long)

    private external fun isPlaying(synthesizerHandle: Long): Boolean

    private external fun setFrequency(synthesizerHandle: Long, frequencyInHz: Float)

    private external fun setVolume(synthesizerHandle: Long, volumeInDb: Float)

    private external fun setWaveTable(synthesizerHandle: Long, wavetable: Int)

    private external fun getCurrentWaveTable(synthesizerHandle: Long): Int

    companion object{
        init {
            System.loadLibrary("core-synth")
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        synchronized(synthesizerMutex) {
            createNativeIfNotExists()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

        synchronized(synthesizerMutex){
            if (synthesizerHandle == 0L){
                return
            }
            delete(synthesizerHandle)
            synthesizerHandle = 0L
        }
    }

    private fun createNativeIfNotExists() {
        if (synthesizerHandle != 0L) {
            return
        }

        synthesizerHandle = create()
    }

    override suspend fun play() = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex){
            createNativeIfNotExists()
            play(synthesizerHandle)
        }
    }

    override suspend fun stop() = withContext(Dispatchers.Default){
        synchronized(synthesizerMutex) {
            createNativeIfNotExists()
            stop(synthesizerHandle)
        }
    }

    override suspend fun isPlaying(): Boolean = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex) {
            createNativeIfNotExists()
            return@withContext isPlaying(synthesizerHandle)
        }
    }

    override suspend fun setFrequency(hz: Hz) = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex) {
            createNativeIfNotExists()
            setFrequency(synthesizerHandle, hz.value)
        }
    }

    override suspend fun setVolume(db: Db) = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex) {
            createNativeIfNotExists()
            setVolume(synthesizerHandle, db.value)
        }
    }

    override suspend fun setWaveTable(wavetable: WaveTable) = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex){
            createNativeIfNotExists()
            setWaveTable(synthesizerHandle, wavetable.ordinal)
        }
    }

    override suspend fun getCurrentWaveTable(): WaveTable = withContext(Dispatchers.Default) {
        synchronized(synthesizerMutex){
            createNativeIfNotExists()
            return@withContext WaveTable.getByOrdinal(getCurrentWaveTable(synthesizerHandle))
        }
    }
}
