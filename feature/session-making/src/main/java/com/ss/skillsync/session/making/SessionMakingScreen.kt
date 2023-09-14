package com.ss.skillsync.session.making

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.session.making.screens.SkillSelectionScreen


@Destination("Matching")
@Composable
fun SessionMakingScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: SessionMakingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.matchedUser) {
        if (state.isMatchFound) {
            snackbarHostState.showSnackbar("Match Found")
            // TODO navigate to MatchFoundScreen
        }
    }

    SessionMakingContent(
        state,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun SessionMakingContent(
    state: SessionMakingState,
    onEvent: (SessionMakingEvent) -> Unit = {},
) {
    AnimatedContent(
        targetState = state,
        label = "SessionMakingContent",
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
            ) togetherWith slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        },
    ) {
        if (it.isSkillSelectionDisplayed) {
            SkillSelectionScreen(
                state = it,
                onEvent = onEvent,
            )
        }
    }
}