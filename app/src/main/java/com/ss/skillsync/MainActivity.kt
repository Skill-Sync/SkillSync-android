package com.ss.skillsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ss.skillsync.commonandroid.DefaultSnackbarHost
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.NavigationParams
import com.ss.skillsync.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreenSetup()

        super.onCreate(savedInstanceState)
        setContent {
            val navParams  by viewModel.navigationParams.collectAsState()
            if (navParams != null) {
                App(navigationParams = navParams!!)
            }
        }
    }

    private fun splashScreenSetup() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isAppReady.not()
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier, navigationParams: NavigationParams) {
    SkillSyncTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = {
                    DefaultSnackbarHost(state = snackbarHostState)
                }
            ) {
                AppNavigation(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it),
                    snackbarHostState = snackbarHostState,
                    navigationParams = navigationParams
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    SkillSyncTheme {
        App(Modifier.fillMaxSize(), NavigationParams())
    }
}