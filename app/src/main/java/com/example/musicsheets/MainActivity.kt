package com.example.musicsheets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature_handle_editor.HandleEditorActivity
import com.example.feature_motion_music.MotionMusicActivity
import com.example.musicsheets.ui.theme.MusicSheetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicSheetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TestGoToMotionAct(context = this)
                }
            }
        }
    }
}

@Composable
fun TestGoToMotionAct(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.background(Color(1233)), onClick = {
            navigateToMotionAct(context)
        }) {
            Text(text = "Motion Music")
        }

        Button(modifier = Modifier.background(Color(1233)), onClick = {
            navigateToHandleEditorAct(context)
        }) {
            Text(text = "HandleEditor")
        }
    }
}

fun navigateToMotionAct(context: Context){
    val intent = Intent(context, MotionMusicActivity::class.java)
    context.startActivity(intent)
}

fun navigateToHandleEditorAct(context: Context){
    val intent = Intent(context, HandleEditorActivity::class.java)
    context.startActivity(intent)
}
