package com.example.feature_motion_music

import com.example.core.sensors.SensorType

interface MotionMusicController {

    fun setSensorListener(listener: (Pair<SensorType, List<Float>>) -> Unit)
}
