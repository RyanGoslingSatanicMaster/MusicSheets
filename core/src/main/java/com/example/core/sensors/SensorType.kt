package com.example.core.sensors

import android.content.Context

enum class SensorType {
    LIGHT{
        override fun getSensor(context: Context): Sensors = Sensors.LightSensor(context)
     },
    GRAVITY{
        override fun getSensor(context: Context): Sensors = Sensors.GravitySensor(context)
    },
    GYROSCOPE{
        override fun getSensor(context: Context): Sensors = Sensors.GyroscopeSensor(context)
    },
    ACCELEROMETER{
        override fun getSensor(context: Context): Sensors = Sensors.AccelerometerSensor(context)
    };

    internal abstract fun getSensor(context: Context): Sensors

    companion object{
        internal fun getSensorType(sensor: Sensors): SensorType{
            when(sensor){
                is Sensors.GravitySensor -> return GRAVITY
                is Sensors.LightSensor -> return LIGHT
                is Sensors.GyroscopeSensor -> return GYROSCOPE
                is Sensors.AccelerometerSensor -> return ACCELEROMETER
                else -> return GRAVITY
            }
        }
    }
}
