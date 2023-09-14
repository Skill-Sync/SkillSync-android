package com.ss.skillsync.session.making

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.session.making.screens.AfterSessionScreen
import com.ss.skillsync.session.making.screens.MatchFoundScreen
import com.ss.skillsync.session.making.screens.MatchSearchingScreen
import com.ss.skillsync.session.making.screens.SkillSelectionScreen


@Destination("Matching")
@Composable
fun SessionMakingScreen(
    viewModel: SessionMakingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

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
        targetState = state.currentDestination,
        label = "SessionMakingContent",
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                tween(500),
                initialOffset = { fullWidth -> fullWidth}
            ) togetherWith slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                tween(500),
                targetOffset = { fullWidth -> -fullWidth }
            )
        },
    ) {
        when (it) {
            SessionMakingDestinations.SkillSelection -> SkillSelectionScreen(
                state = state,
                onEvent = onEvent,
            )

            SessionMakingDestinations.MatchSearching -> MatchSearchingScreen(
                state = state,
                onEvent = onEvent
            )

            SessionMakingDestinations.MatchFoundScreen -> MatchFoundScreen(
                state = state,
                onEvent = onEvent,
            )

            SessionMakingDestinations.AfterSessionScreen -> AfterSessionScreen(
                state = state,
                onEvent = onEvent
            )
        }
    }
}