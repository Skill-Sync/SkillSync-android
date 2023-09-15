package com.ss.skillsync.mentorhome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */

@Composable
fun MentorHomeScreen(
    navigator: MentorHomeNavigator,
    viewModel: MentorHomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.navigationDestination) {
        when (val navigationDestination = state.navigationDestination) {
            MentorHomeDestination.SignInScreen -> navigator.navigateToSignInScreen()
            is MentorHomeDestination.SessionScreen -> navigator.navigateToSessionScreen(
                navigationDestination.sessionId
            )

            null -> {
                return@LaunchedEffect
            }
        }
    }

    MentorHomeContent(
        state,
        viewModel::onEvent,
    )
}

@Composable
fun MentorHomeContent(
    state: MentorHomeState,
    onEvent: (MentorHomeEvent) -> Unit,
) {
    ScreenColumn()
}


@Preview
@Composable
fun MentorHomeScreenPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            MentorHomeContent(MentorHomeState(), {})
        }
    }
}