package com.example.core.sensors

import android.content.Context

enum class SensorType {
    LIGHT{
        override fun getSensor(context: Context): Sensors = Sensors.LightSensor(context)

        override val listSize get() = 1
     },
    GRAVITY{
        override fun getSensor(context: Context): Sensors = Sensors.GravitySensor(context)

        //TODO test gravity sensor
        override val listSize get() = 0
    },
    GYROSCOPE{
        override fun getSensor(context: Context): Sensors = Sensors.GyroscopeSensor(context)

        //TODO test gyroscope sensor
        override val listSize get() = 0
    },
    ACCELEROMETER{
        override fun getSensor(context: Context): Sensors = Sensors.AccelerometerSensor(context)

        override val listSize get() = 3
    },
    PROXIMITY{
        override fun getSensor(context: Context): Sensors = Sensors.ProximitySensor(context)

        override val listSize get() = 1
    };

    internal abstract fun getSensor(context: Context): Sensors

    abstract val listSize: Int

    companion object{
        internal fun getSensorType(sensor: Sensors): SensorType{
            when(sensor){
                is Sensors.GravitySensor -> return GRAVITY
                is Sensors.LightSensor -> return LIGHT
                is Sensors.GyroscopeSensor -> return GYROSCOPE
                is Sensors.AccelerometerSensor -> return ACCELEROMETER
                is Sensors.ProximitySensor -> return PROXIMITY
                else -> return GRAVITY
            }
        }
    }
}
