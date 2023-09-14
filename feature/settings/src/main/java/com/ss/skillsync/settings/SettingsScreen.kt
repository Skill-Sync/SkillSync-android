package com.ss.skillsync.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.R
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */

@Destination
@Composable
fun SettingsScreen(
    navigator: SettingsNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val defaultErrorText = stringResource(R.string.something_went_wrong)
    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(
                message = it.message ?: defaultErrorText,
                duration = SnackbarDuration.Short,
            )
        }
    }

    LaunchedEffect(key1 = state.navigatedUp) {
        navigator.popBackStack()
    }

    LaunchedEffect(key1 = state.navigationDestination) {
        when (state.navigationDestination) {
            SettingsDestinations.SignInScreen -> navigator.navigateToSignInScreen()
            SettingsDestinations.PrivacyPolicyScreen -> navigator.navigateToPrivacyPolicyScreen()
            SettingsDestinations.EditProfileScreen -> navigator.navigateToEditProfileScreen()
            null -> return@LaunchedEffect
        }
    }

    SettingsContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
fun SettingsContent(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
) {
    ScreenColumn(
        isBackDisplayed = true,
        onBackClicked = { onEvent(SettingsEvent.OnBackPressed) },
        screenLabel = "Settings"
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            SettingsContent(SettingsState(), {})
        }
    }
}