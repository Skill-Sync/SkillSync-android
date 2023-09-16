package com.ss.skillsync.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Settings
import com.ss.skillsync.settings.components.ProfileSection
import com.ss.skillsync.settings.components.SettingsList
import com.ss.skillsync.commonandroid.R as comRes

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

    val defaultErrorText = stringResource(comRes.string.something_went_wrong)
    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(
                message = it.message ?: defaultErrorText,
                duration = SnackbarDuration.Short,
            )
        }
        viewModel.resetEvents()
    }

    LaunchedEffect(key1 = state.navigatedUp) {
        if (state.navigatedUp)
            navigator.popBackStack()
        viewModel.resetEvents()
    }

    LaunchedEffect(key1 = state.navigationDestination) {
        when (state.navigationDestination) {
            SettingsDestinations.SignInScreen -> navigator.navigateToSignInScreen()
            SettingsDestinations.PrivacyPolicyScreen -> navigator.navigateToPrivacyPolicyScreen()
            SettingsDestinations.EditProfileScreen -> navigator.navigateToEditProfileScreen()
            null -> return@LaunchedEffect
        }
        viewModel.resetEvents()
    }

    SettingsContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
fun SettingsContent(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
) {
    val settingsList = listOf(
        Setting(
            icon = rememberVectorPainter(image = Icons.Outlined.AccountCircle),
            title = stringResource(R.string.edit_profile),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onClick = { onEvent(SettingsEvent.EditProfileClicked) },
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_notification),
            title = stringResource(R.string.notifications),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onSwitch = { onEvent(SettingsEvent.SettingsUpdated(Settings(notificationsEnabled = it))) },
            isChecked = state.settings.notificationsEnabled ?: false,
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_payment),
            title = stringResource(R.string.payment),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onClick = {  },
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_dark),
            title = stringResource(R.string.dark_mode),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onSwitch = { onEvent(SettingsEvent.SettingsUpdated(Settings(darkMode = it))) },
            isChecked = state.settings.darkMode ?: false,
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_privacy),
            title = stringResource(R.string.privacy_policy),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onClick = { onEvent(SettingsEvent.PrivacyPolicyClicked) },
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_help),
            title = stringResource(R.string.help_center),
            itemColor = MaterialTheme.colorScheme.onBackground,
            onClick = {  },
        ),
        Setting(
            icon = painterResource(id = R.drawable.ic_logout),
            title = stringResource(R.string.logout),
            itemColor = MaterialTheme.colorScheme.error,
            onClick = { onEvent(SettingsEvent.LogoutClicked) },
        ),
    )

    ScreenColumn(
        isBackDisplayed = true,
        onBackClicked = { onEvent(SettingsEvent.OnBackPressed) },
        screenLabel = "Settings",
        isLoading = state.isLoading,
    ) {
        ProfileSection(user = state.activeUser)
        SettingsList(settingsList = settingsList)
    }
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