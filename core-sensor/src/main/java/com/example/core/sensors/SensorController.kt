package com.example.core.sensors

import android.content.Context
import androidx.activity.ComponentActivity

interface SensorController {

    fun setListener(listener: (Pair<SensorType, List<Float>>) -> Unit)

    fun removeListener(type: SensorType)

    companion object {
        fun getSensorController(
            sensorTypeList: List<SensorType>,
            activity: ComponentActivity
        ): SensorController = SensorControllerImpl(sensorTypeList, activity)
    }
}
