package com.ss.skillsync.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ss.skillsync.presentation.theme.SkillSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenSetup()
        setContent {
            SkillSyncTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(Modifier.fillMaxSize())
                }
            }
        }
    }

    private fun splashScreenSetup() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                // TODO: Add Auth Checking And Navigate to correct navGraph
                Thread.sleep(1000)
                false
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Text(
        text = "Hello, SkillSync",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    SkillSyncTheme {
        App(Modifier.fillMaxSize())
    }
}