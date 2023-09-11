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
            viewModel.resetErrors()
        }
    }
    HomeContent(
        state = state,
        onEvent = viewModel::onEvent,
        navigator = navigator
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    navigator: HomeNavigator,
) {
    ScreenColumn(isLoading = state.isLoading) {

        TopHomeBar(
            state.activeUser,
            onProfileClicked = { navigator.navigateToProfile() }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SkillSyncTheme {
//        HomeContent()
    }
}