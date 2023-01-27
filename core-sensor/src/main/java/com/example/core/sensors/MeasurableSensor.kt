package com.example.core.sensors

internal abstract class MeasurableSensor(protected val sensorType: Int) {

    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: ((List<Float>) -> Unit)? = null){
        onSensorValuesChanged = listener
    }

    fun removeOnSensorValuesChangedListener(){
        onSensorValuesChanged = null
    }
}
