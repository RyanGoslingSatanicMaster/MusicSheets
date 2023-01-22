package com.example.feature_motion_music

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.BaseActivity
import com.example.common.BaseApp
import com.example.common.NavigateToHost
import com.example.core.sensors.SensorType
import com.example.feature_motion_music.di.MotionMusicAppComponent
import com.example.feature_motion_music.di.MotionMusicComponent
import com.example.feature_motion_music.ui.theme.MusicSheetsTheme
import javax.inject.Inject

class MotionMusicActivity : BaseActivity() {

    private lateinit var component: MotionMusicComponent

    private val viewModel by lazy {
        getViewModel<MotionMusicViewModel>()
    }

    @Inject
    lateinit var navigateToHost: NavigateToHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicSheetsTheme {
                MotionMusicLayout()
            }
        }
    }

    override fun inject() {
        component =
            ((application as BaseApp).getAppComponent() as MotionMusicAppComponent)
                .getMotionMusicComponentBuilder().activity(this).build()
        component.inject(this)
        navigateToHost = component.getNavHost()
    }

    @Composable
    fun MotionMusicLayout() {
        val state = viewModel.sensorStates.collectAsState()
        MotionDetecter(map = state.value)
    }

    @Composable
    fun MotionDetecter(map: Map<SensorType, List<Float>>) {
        Column(modifier = Modifier.fillMaxSize()) {
            map.forEach {
                textSensor(type = it.key, name = it.value.toString())
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun textSensor(type: SensorType, name: String) {
    Text(text = "${type.name} $name!", color = Color.Black)
}
