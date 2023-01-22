package com.example.core.sensors

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class SensorController(
    sensorTypeList: List<SensorType>,
    private val activity: ComponentActivity,
) {

    private val androidSensors: List<Sensors>

    init {
        androidSensors = sensorTypeList.provideSensors()
        activity.lifecycle.addObserver(object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when(event){
                    Lifecycle.Event.ON_START -> androidSensors.forEach {
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

    fun List<SensorType>.provideSensors(): List<Sensors>{
        val list = mutableListOf<Sensors>()
        forEach {
            list.add(it.getSensor(activity))
        }
        return list
    }

    fun setListener(listener: (Pair<SensorType, List<Float>>) -> Unit){
        androidSensors.forEach{ sensor ->
            sensor.setOnSensorValuesChangedListener {
                listener(SensorType.getSensorType(sensor) to it)
            }
        }
    }

    fun removeListener(type: SensorType){
        androidSensors.find{ SensorType.getSensorType(it) == type}?.removeOnSensorValuesChangedListener()
    }

}
