package com.ss.skillsync.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.lifecycle.lifecycleScope
import com.ss.skillsync.presentation.theme.SkillSyncTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                true
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Text(
        text = "Hello SkillNest",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun AppPreview() {
    SkillSyncTheme {
        App(Modifier.fillMaxSize())
    }
}