package com.ss.skillsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ss.skillsync.commonandroid.DefaultSnackbarHost
import com.ss.skillsync.commonandroid.composition.LocalMeetingManager
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.meeting.api.MeetingManager
import com.ss.skillsync.model.NavigationParams
import com.ss.skillsync.navigation.AppNavigation
import com.ss.skillsync.navigation.NavGraphs
import com.ss.skillsync.navigation.component.SSBottomNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var meetingManager: MeetingManager


    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreenSetup()

        super.onCreate(savedInstanceState)
        setContent {
            val navParams by viewModel.navigationParams.collectAsState()
            if (navParams != null) {
                CompositionLocalProvider(LocalMeetingManager provides meetingManager) {
                    App(navigationParams = navParams!!)
                }
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(modifier: Modifier = Modifier, navigationParams: NavigationParams) {
    SkillSyncTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberAnimatedNavController()
            NavGraphs.create(navigationParams)
            Scaffold(
                snackbarHost = {
                    DefaultSnackbarHost(state = snackbarHostState)
                },
                bottomBar = {
                    SSBottomNavigation(navigator = navController, navigationParams = navigationParams)
                },
            ) {
                AppNavigation(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it),
                    snackbarHostState = snackbarHostState,
                    navController = navController,
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
