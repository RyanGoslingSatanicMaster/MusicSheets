package com.example.feature_motion_music.di

import androidx.activity.ComponentActivity
import com.example.common.di.annotations.PerActivity
import com.example.core.sensors.SensorController
import com.example.core.sensors.SensorType
import com.example.core_synth.Synthesizer
import dagger.Module
import dagger.Provides

@Module
object MotionMusicModule {

    val listSensorType = listOf(
        SensorType.LIGHT,
        SensorType.PROXIMITY,
        SensorType.ACCELEROMETER
    )

    @PerActivity
    @JvmStatic
    @Provides
    fun provideSensorController(
        activity: ComponentActivity
    ): SensorController = SensorController.getSensorController(listSensorType, activity)

    @PerActivity
    @JvmStatic
    @Provides
    fun provideSynthController(
    ): Synthesizer = Synthesizer.getTestSynth()

}
