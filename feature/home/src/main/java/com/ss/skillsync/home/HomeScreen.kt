package com.ss.skillsync.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.Shark
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.home.components.MentorsSlider
import com.ss.skillsync.home.components.SessionsScheduledList
import com.ss.skillsync.home.components.StartMatchSection
import com.ss.skillsync.home.components.TopHomeBar
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.User

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
        when (val navDestination = state.navDestination) {
            HomeNavDestinations.Profile -> navigator.navigateToProfile()
            HomeNavDestinations.Settings -> navigator.navigateToSettings()
            HomeNavDestinations.Match -> navigator.navigateToMatch()
            is HomeNavDestinations.MentorProfile -> {
                navigator.navigateToMentorProfile()
            }

            is HomeNavDestinations.SessionDetails -> {
                navigator.navigateToSessionDetails(session = navDestination.session)
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

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    val screenPadding = 16.dp
    ScreenColumn(
        isLoading = state.isLoading,
        contentPadding = PaddingValues(0.dp),
        screenColor = Shark
    ) {
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
        Spacer(modifier = Modifier.weight(0.3f))
        StartMatchSection(
            modifier = Modifier
                .padding(
                    start = screenPadding,
                    end = screenPadding,
                    bottom = screenPadding,
                ),
            onMatchClicked = {
                onEvent(HomeEvent.OnMatchClicked)
            })
        Spacer(modifier = Modifier.weight(0.1f))
        SessionsScheduledList(
            modifier = Modifier
                .weight(3f)
                .padding(horizontal = screenPadding),
            sessions = state.scheduledSessions,
            onSessionClicked = {
                onEvent(HomeEvent.OnSessionClicked(it))
            }
        )
        Spacer(modifier = Modifier.weight(0.2f))
        MentorsSlider(
            mentorsList = state.suggestedMentors,
            onMentorClicked = { mentor -> onEvent(HomeEvent.OnMentorClicked(mentor)) },
        )
        Spacer(modifier = Modifier.weight(0.3f))
    }
}

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