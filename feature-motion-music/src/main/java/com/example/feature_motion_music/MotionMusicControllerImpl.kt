package com.example.feature_motion_music

import com.example.core.sensors.SensorController
import com.example.core.sensors.SensorType
import com.example.core_synth.Hz
import com.example.core_synth.Synthesizer
import com.example.core_synth.dB
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MotionMusicControllerImpl @Inject constructor(
    private val sensorController: SensorController,
    private val synthesizer: Synthesizer
): MotionMusicController, CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private var listener: ((Pair<SensorType, List<Float>>) -> Unit)? = null

    override fun setSensorListener(listener: (Pair<SensorType, List<Float>>) -> Unit) {
        this.listener = listener
        sensorController.setListener{
            listener(it)
            GlobalScope.launch {
                synthCallback(it)
            }
        }
    }

    private suspend fun synthCallback(pair: Pair<SensorType, List<Float>>){
        when(pair.first){
            SensorType.ACCELEROMETER -> triggerAccelerometer(pair.second)
            SensorType.PROXIMITY -> triggerProximity(pair.second.get(0))
            SensorType.LIGHT -> triggerLight(pair.second.get(0))
            else -> {
            }
        }
    }

    private suspend fun triggerAccelerometer(list: List<Float>){
        if (list.max() > 10) {
            if (!synthesizer.isPlaying())
                synthesizer.play()
            else
                synthesizer.stop()
        }
    }

    private suspend fun triggerProximity(proximity: Float) = synthesizer.setVolume(proximity.dB)

    private suspend fun triggerLight(light: Float) = synthesizer.setFrequency(light.Hz)

}
