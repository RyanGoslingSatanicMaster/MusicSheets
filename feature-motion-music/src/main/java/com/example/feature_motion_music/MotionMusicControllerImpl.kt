package com.example.feature_motion_music

import android.hardware.SensorManager
import com.example.core.sensors.SensorController
import com.example.core.sensors.SensorType
import com.example.core_synth.Hz
import com.example.core_synth.Synthesizer
import com.example.core_synth.WaveTable
import com.example.core_synth.dB
import kotlinx.coroutines.*
import java.lang.Math.sqrt
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MotionMusicControllerImpl @Inject constructor(
    private val sensorController: SensorController,
    private val synthesizer: Synthesizer
): MotionMusicController, CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private var listener: ((Pair<SensorType, List<Float>>) -> Unit)? = null

    private var acceleration = 10f
    private var currentAcceleration = SensorManager.GRAVITY_EARTH
    private var lastAcceleration = SensorManager.GRAVITY_EARTH

    // TODO rewrite to synthesizer interface
    private var currentWave = WaveTable.TRIANGLE

    override fun setSensorListener(listener: (Pair<SensorType, List<Float>>) -> Unit) {
        this.listener = listener
        runBlocking {
            synthesizer.play()
            synthesizer
            synthesizer.setWaveTable(currentWave)
            synthesizer.setVolume((-35).dB)
            synthesizer.setFrequency(2000.Hz)
        }
        sensorController.setListener{
            listener(it)
            GlobalScope.launch {
                synthCallback(it)
            }
        }
    }

    private suspend fun synthCallback(pair: Pair<SensorType, List<Float>>){
        when(pair.first){
            //SensorType.ACCELEROMETER -> triggerAccelerometer(pair.second)
            //SensorType.PROXIMITY -> triggerProximity(pair.second.get(0))
            //SensorType.LIGHT -> triggerLight(pair.second.get(0))
            else -> {
            }
        }
    }

    private suspend fun triggerAccelerometer(list: List<Float>){
        val x = list[0]
        val y = list[1]
        val z = list[2]
        lastAcceleration = currentAcceleration

        // Getting current accelerations
        // with the help of fetched x,y,z list
        currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta: Float = currentAcceleration - lastAcceleration
        acceleration = acceleration * 0.9f + delta

        // Display a Toast message if
        // acceleration value is over 12
        if (acceleration > 12) {
            if(synthesizer.isPlaying())
                synthesizer.stop()
            else
                synthesizer.play()
        }
    }

    private suspend fun triggerProximity(proximity: Float){
        if (proximity > 1 || currentWave != WaveTable.TRIANGLE)
            synthesizer.setWaveTable(WaveTable.SAW)
        else
            synthesizer.setWaveTable(WaveTable.TRIANGLE)
    }

    private suspend fun triggerLight(light: Float){
        if (light >= 5.0f)
            synthesizer.setVolume(0.dB)
        else
            synthesizer.setVolume(-20.dB)
    }

}
