package com.example.feature_motion_music

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.core.sensors.SensorController
import com.example.core.sensors.SensorType
import com.example.feature_motion_music.di.MotionMusicModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MotionMusicViewModel @Inject constructor(
    private val sensorController: SensorController
): ViewModel() {

    private val startValues: Map<SensorType, List<Float>> = setDefaultValues()

    private val _sensorStates = MutableStateFlow(startValues)

    val sensorStates: StateFlow<Map<SensorType, List<Float>>> = _sensorStates

    init {
        sensorController.setListener(::onSensorValueChange)
    }

    private fun setDefaultValues(): Map<SensorType, List<Float>>{
        val map = mutableMapOf<SensorType, List<Float>>()
        MotionMusicModule.listSensorType.forEach{
            map.plus(it to listOf())
        }
        return map
    }


    fun onSensorValueChange(pair: Pair<SensorType, List<Float>>){
        _sensorStates.value = _sensorStates.value.toMutableMap().apply {
            get(pair.first)?.let {
                minus(pair.first)
                plus(pair.first to it)
            }
        }
    }
}
