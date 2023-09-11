package com.ss.skillsync.home

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.home.components.TopHomeBar
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
                navigator.navigateToMentorProfile(mentor = navDestination.mentor)
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
    ScreenColumn(isLoading = state.isLoading) {
        TopHomeBar(
            state.activeUser,
            onProfileClicked = { onEvent(HomeEvent.OnProfileClicked) },
            onSettingsClicked = { onEvent(HomeEvent.OnSettingsClicked) },
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SkillSyncTheme {
        HomeContent(
            HomeState(
                isLoading = false,
                activeUser = User(name = "Mohannad El-Sayeh")
            ),
            {}
        )
    }
}