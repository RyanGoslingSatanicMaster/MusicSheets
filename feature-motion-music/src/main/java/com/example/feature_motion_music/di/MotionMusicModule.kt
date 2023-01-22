package com.example.feature_motion_music.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.example.common.di.annotations.PerActivity
import com.example.core.sensors.SensorController
import com.example.core.sensors.SensorType
import dagger.Module
import dagger.Provides

@Module
object MotionMusicModule {

    val listSensorType = listOf(
        SensorType.LIGHT,
        SensorType.GRAVITY,
        SensorType.GYROSCOPE,
        SensorType.ACCELEROMETER
    )

    @PerActivity
    @JvmStatic
    @Provides
    fun provideSensorController(
        activity: ComponentActivity
    ): SensorController = SensorController(listSensorType, activity)
}
