package com.example.core.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

internal abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    private val delay: Int = SensorManager.SENSOR_DELAY_NORMAL,
    sensorType: Int
): MeasurableSensor(sensorType), SensorEventListener {

    override val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun startListening() {
        Log.v(sensorFeature, doesSensorExist.toString())
        if (!doesSensorExist) return
        if (!::sensorManager.isInitialized && sensor == null){
            sensorManager = context.getSystemService(SensorManager::class.java) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
        }
        sensor?.let {
            sensorManager.registerListener(this, it, delay)
        }
    }

    override fun stopListening() {
        if (!doesSensorExist || ::sensorManager.isInitialized) return
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!doesSensorExist) return

        if (event?.sensor?.type == sensorType)
            onSensorValuesChanged?.invoke(event.values.toList())
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
}
