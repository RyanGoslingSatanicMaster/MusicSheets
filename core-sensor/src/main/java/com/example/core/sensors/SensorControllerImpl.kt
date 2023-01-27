package com.example.core.sensors

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

internal class SensorControllerImpl(
    sensorTypeList: List<SensorType>,
    private val activity: ComponentActivity,
): SensorController {

    private val androidSensors: List<Sensors>

    private var isListenerSet = false

    init {
        androidSensors = sensorTypeList.provideSensors()
        activity.lifecycle.addObserver(object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when(event){
                    Lifecycle.Event.ON_START -> androidSensors.forEach {
                        if (isListenerSet)
                            it.startListening()
                    }
                    Lifecycle.Event.ON_STOP -> androidSensors.forEach {
                        it.stopListening()
                    }
                    else -> {}
                }
            }
        })
    }

    private fun List<SensorType>.provideSensors(): List<Sensors>{
        val list = mutableListOf<Sensors>()
        forEach {
            list.add(it.getSensor(activity))
        }
        return list
    }

    override fun setListener(listener: (Pair<SensorType, List<Float>>) -> Unit){
        isListenerSet = true
        androidSensors.forEach{ sensor ->
            sensor.setOnSensorValuesChangedListener {
                listener(SensorType.getSensorType(sensor) to it)
            }
        }
    }

    override fun removeListener(type: SensorType){
        isListenerSet = false
        androidSensors.find{ SensorType.getSensorType(it) == type}?.removeOnSensorValuesChangedListener()
    }

}
