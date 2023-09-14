package com.ss.skillsync.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.Shark
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.home.components.MentorsSlider
import com.ss.skillsync.home.components.SessionDetailsBottomSheet
import com.ss.skillsync.home.components.SessionsScheduledList
import com.ss.skillsync.home.components.StartMatchSection
import com.ss.skillsync.home.components.TopHomeBar
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.User
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */

@Destination("Home")
@Composable
fun HomeScreen(
    navigator: HomeNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            snackbarHostState.showSnackbar(
                message = state.error!!.message ?: "Something went wrong.",
                duration = SnackbarDuration.Short,
            )
            viewModel.resetEvents()
        }
    }

    LaunchedEffect(key1 = state.navDestination) {
        when (state.navDestination) {
            HomeNavDestinations.Profile -> navigator.navigateToProfile()
            HomeNavDestinations.Settings -> navigator.navigateToSettings()
            HomeNavDestinations.Match -> navigator.navigateToMatch()
            is HomeNavDestinations.MentorProfile -> {
                navigator.navigateToMentorProfile()
            }

            null -> return@LaunchedEffect
        }
        viewModel.resetEvents()
    }
    HomeContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    val screenPadding = 16.dp
    val sheetState =
        rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = sheetState.hasPartiallyExpandedState) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    LaunchedEffect(key1 = state.selectedSession) {
        if (state.selectedSession != null) {
            sheetState.expand()
        } else {
            sheetState.hide()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopHomeBar(
                state.activeUser,
                modifier = Modifier.padding(
                    start = screenPadding,
                    end = screenPadding,
                    top = screenPadding,
                ),
                onProfileClicked = { onEvent(HomeEvent.OnProfileClicked) },
                onSettingsClicked = { onEvent(HomeEvent.OnSettingsClicked) },
            )
        },
    ) { contentPadding ->
        ScreenColumn(
            isLoading = state.isLoading,
            contentPadding = contentPadding,
            screenColor = Shark
        ) {
            StartMatchSection(
                onMatchClicked = { onEvent(HomeEvent.OnMatchClicked) },
                modifier = Modifier.spacePadding(screenPadding)
            )
            SessionsScheduledList(modifier = Modifier
                .weight(3f)
                .spacePadding(screenPadding),
                sessions = state.scheduledSessions,
                onSessionClicked = {
                    onEvent(HomeEvent.OnSessionClicked(it))
                })
            MentorsSlider(
                mentorsList = state.suggestedMentors,
                onMentorClicked = { mentor -> onEvent(HomeEvent.OnMentorClicked(mentor)) },
                modifier = Modifier.padding(top = screenPadding),
            )
            AnimatedVisibility(visible = state.selectedSession != null) {
                state.selectedSession?.let {
                    SessionDetailsBottomSheet(
                        session = it,
                        onDismiss = { onEvent(HomeEvent.OnSessionDismissed) },
                        sheetState = sheetState,
                        modifier = Modifier.padding(
                            WindowInsets.navigationBars.asPaddingValues(
                                LocalDensity.current
                            )
                        )
                    )
                }
            }
        }
    }
}

fun Modifier.spacePadding(space: Dp) = this.padding(top = space, start = space, end = space)


@Preview
@Composable
fun HomeScreenPreview() {
    SkillSyncTheme {
        val mentors = List(10) {
            Mentor(
                it.toString(),
                "Mentor $it",
                "https://picsum.photos/200/300",
                "Software Engineer",
            )
        }
        val sessions = List(10) {
            Session(
                it.toString(),
                "Session $it",
                "https://picsum.photos/200/300",
                "Software Engineer",
                "3:00 PM",
                dateCalendar = Calendar.getInstance(),
                scheduledDate = "12/12/2021",
            )
        }
        HomeContent(
            HomeState(
                isLoading = false,
                activeUser = User(name = "Mohannad El-Sayeh"),
                scheduledSessions = sessions,
                suggestedMentors = mentors,
            ),
            {}
        )
    }
}