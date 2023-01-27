package com.example.feature_motion_music

import androidx.lifecycle.ViewModel
import com.example.core.sensors.SensorType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MotionMusicViewModel @Inject constructor(
    private val controller: MotionMusicController
) : ViewModel() {

    private val _sensorStates = MutableStateFlow<Map<SensorType, List<Float>>>(mapOf())

    val sensorStates = _sensorStates.asStateFlow()

    init {
        controller.setSensorListener(::onSensorValueChange)
    }

    /*private fun setDefaultValues(): Map<SensorType, List<Float>> {
        val map = mutableMapOf<SensorType, List<Float>>()
        MotionMusicModule.listSensorType.forEach {
            map.plus(it to listOf())
        }
        return map
    }*/


    fun onSensorValueChange(pair: Pair<SensorType, List<Float>>) {
        _sensorStates.update {
            it.toMutableMap().apply {
                get(pair.first)?.let {
                    minus(pair.first)
                }
                put(pair.first, pair.second)
            }
        }
    }
}
