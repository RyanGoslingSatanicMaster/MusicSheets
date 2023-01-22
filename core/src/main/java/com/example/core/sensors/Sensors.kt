package com.example.core.sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager

sealed class Sensors(
    context: Context,
    sensorFeature: String,
    delay: Int = SensorManager.SENSOR_DELAY_NORMAL,
    sensorType: Int
) : AndroidSensor(context, sensorFeature, delay, sensorType) {

    class LightSensor(
        context: Context
    ) : Sensors(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
        sensorType = Sensor.TYPE_LIGHT
    )

    class ProximitySensor(
        context: Context
    ) : Sensors(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_PROXIMITY,
        sensorType = Sensor.TYPE_PROXIMITY
    )

    class AccelerometerSensor(
        context: Context
    ) : Sensors(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_HINGE_ANGLE,
        sensorType = Sensor.TYPE_ACCELEROMETER
    )

    class GravitySensor(
        context: Context
    ) : Sensors(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_COMPASS,
        sensorType = Sensor.TYPE_GRAVITY
    )

    class GyroscopeSensor(
        context: Context
    ) : Sensors(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_GYROSCOPE,
        sensorType = Sensor.TYPE_GYROSCOPE
    )

}

